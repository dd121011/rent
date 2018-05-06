package com.scrats.rent.util;

import org.apache.log4j.Logger;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;

/**
 * Created with jointstarc.
 * Description:  properties  资源文件解析工具
 * User: lol.
 * Date: 2016/12/14.
 * Time: 17:05.
 */
@SuppressWarnings("unchecked")
public class PropertiesUtil {

    private static final Logger logger = Logger.getLogger(PropertiesUtil.class);

    /**
     * 获取所有属性，返回一个map,不常用
     * 可以试试props.putAll(t)
     */
    public static Map getAllProperty(String fileName) {
        Properties p = loadPropertyInstance(fileName);
        Map map = new HashMap();
        Enumeration enu = p.propertyNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = p.getProperty(key);
            map.put(key, value);
        }
        return map;
    }

    /*
     * 单属性读写
     */

    public static boolean setProperty(String fileName, String propertyName, String propertyValue) {
        try {
            Properties p = loadPropertyInstance(fileName);
            p.setProperty(propertyName, propertyValue);
            String comment = "Update '" + propertyName + "' value";//添加注释
            return storePropertyInstance(fileName, p, comment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean clearProperty(String fileName, String propertyName) {
        try {
            Properties p = loadPropertyInstance(fileName);
            p.setProperty(propertyName, "");
            String comment = propertyName;
            return storePropertyInstance(fileName, p, comment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean removeProperty(String fileName, String propertyName) {
        try {
            Properties p = loadPropertyInstance(fileName);
            p.remove(propertyName);
            String comment = propertyName;
            return storePropertyInstance(fileName, p, comment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static String getProperty(String fileName, String propertyName) {
        try {
            Properties p = loadPropertyInstance(fileName);
            return p.getProperty(propertyName);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getProperty(String fileName, String propertyName, String defaultValue) {
        try {
            Properties p = loadPropertyInstance(fileName);
            return p.getProperty(propertyName, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 多属性读写
     */

    public static boolean setProperty(String fileName, Map<String, String> propertyMap) {
        try {
            Properties p = loadPropertyInstance(fileName);
            for (String name : propertyMap.keySet()) {
                p.setProperty(name, propertyMap.get(name));
            }
            String comment = "Update '" + propertyMap.keySet().toString() + "' value";
            return storePropertyInstance(fileName, p, comment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 这么随便的代码，却是极好的！
     *
     * @param fileName
     * @param propertyArray
     * @return
     */
    public static boolean setProperty(String fileName, String... propertyArray) {
        if (propertyArray == null || propertyArray.length % 2 != 0) {
            throw new IllegalArgumentException("make sure 'propertyArray' argument is 'ket/value' pairs");
        }
        try {
            Properties p = loadPropertyInstance(fileName);
            for (int i = 0; i < propertyArray.length / 2; i++) {
                p.setProperty(propertyArray[i * 2], propertyArray[i * 2 + 1]);
            }
            String comment = "Update '" + propertyArray[0] + "..." + "' value";
            return storePropertyInstance(fileName, p, comment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 方法返回布尔值，为true时表示成功
     * 详细数据见传入参数propertyMap（此处采用传引用的方式）
     *
     * @param fileName
     * @param propertyMap
     * @return
     */
    public static boolean getProperty(String fileName, Map<String, String> propertyMap) {
        try {
            Properties p = loadPropertyInstance(fileName);
            for (String name : propertyMap.keySet()) {
                propertyMap.put(name, p.getProperty(name, propertyMap.get(name)));
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /*
     * List属性值读写
     */

    public static boolean setProperty(String fileName, String propertyName, List<String> propertyValueList) {
        try {
            Properties p = loadPropertyInstance(fileName);
            StringBuilder propertyValue = new StringBuilder();
            if (propertyValueList != null && propertyValueList.size() > 0) {
                for (String value : propertyValueList) {
                    propertyValue.append(
                            value.replaceAll("(\\\\)+$", "").replaceAll("\\\\", "\\\\\\\\").replaceAll(";", "\\\\;") +
                                    ";");
                }
            }
            p.setProperty(propertyName, propertyValue.toString());
            String comment = "Update '" + propertyName + "' value";
            return storePropertyInstance(fileName, p, comment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean appendProperty(String fileName, String propertyName, List<String> propertyValueList) {
        try {
            Properties p = loadPropertyInstance(fileName);
            StringBuilder propertyValue = new StringBuilder();
            for (String value : propertyValueList) {
                propertyValue.append(
                        value.replaceAll("(\\\\)+$", "").replaceAll("\\\\", "\\\\\\\\").replaceAll(";", "\\\\;") +
                                ";");
            }
            p.setProperty(propertyName, p.getProperty(propertyName) + propertyValue.substring(1));
            String comment = "Update '" + propertyName + "' value";
            return storePropertyInstance(fileName, p, comment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean appendProperty(String fileName, String propertyName, String propertyValue) {
        try {
            Properties p = loadPropertyInstance(fileName);
            p.setProperty(propertyName, p.getProperty(propertyName, "") +
                    propertyValue.replaceAll("(\\\\)+$", "").replaceAll("\\\\", "\\\\\\\\").replaceAll(";", "\\\\;") +
                    ";");
            String comment = "Update '" + propertyName + "' value";
            return storePropertyInstance(fileName, p, comment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<String> getPropertyList(String fileName, String propertyName, String defaultValue) {
        try {
            Properties p = loadPropertyInstance(fileName);
            String v = p.getProperty(propertyName, defaultValue);
            String[] iA = v.split("(?<!\\\\);");
            for (int i = 0; i < iA.length; i++) {
                iA[i] = iA[i].replaceAll("(\\\\)+$", "").replaceAll("\\\\;", ";").replaceAll("\\\\\\\\", "\\\\");
            }
            return Arrays.asList(iA);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * MAP属性值读写
     */

    public static boolean setProperty(String fileName, String propertyName, Map<String, String> propertyValueMap) {
        try {
            Properties p = loadPropertyInstance(fileName);
            StringBuilder propertyValue = new StringBuilder();
            if (propertyValueMap != null && propertyValueMap.size() > 0) {
                for (String key : propertyValueMap.keySet()) {
                    propertyValue.append(
                            key.replaceAll("\\\\", "\\\\\\\\").replaceAll("(\\\\)+$", "").replaceAll("\\,", "\\\\,").replaceAll(";", "\\\\;") +
                                    "," +
                                    propertyValueMap.get(key).replaceAll("(\\\\)+$", "").replaceAll("\\\\", "\\\\\\\\").replaceAll("\\,", "\\\\,").replaceAll(";", "\\\\;") +
                                    ";");
                }
            }
            p.setProperty(propertyName, propertyValue.toString());
            String comment = "Update '" + propertyName + "' value";
            return storePropertyInstance(fileName, p, comment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean appendProperty(String fileName, String propertyName, Map<String, String> propertyValueMap) {
        try {
            Map<String, String> combinePropertyValueMap = getPropertyMap(fileName, propertyName, "");
            combinePropertyValueMap.putAll(propertyValueMap);
            return setProperty(fileName, propertyName, combinePropertyValueMap);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean appendProperty(String fileName, String propertyName, String propertyKey, String propertyValue) {
        try {
            Properties p = loadPropertyInstance(fileName);
            p.setProperty(propertyName, p.getProperty(propertyName, "") +
                    propertyKey.replaceAll("(\\\\)+$", "").replaceAll("\\\\", "\\\\\\\\").replaceAll("\\,", "\\\\,").replaceAll(";", "\\\\;") +
                    "," +
                    propertyValue.replaceAll("(\\\\)+$", "").replaceAll("\\\\", "\\\\\\\\").replaceAll("\\,", "\\\\,").replaceAll(";", "\\\\;") +
                    ";");
            String comment = "Update '" + propertyName + "." + propertyKey + "' value";
            return storePropertyInstance(fileName, p, comment);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 返回Map格式封装的属性值
     * 其中，Map以HashMap创建，若要求排序的话，推荐外部排序
     *
     * @param fileName
     * @param propertyName
     * @param defaultValue
     * @return
     */
    public static Map<String, String> getPropertyMap(String fileName, String propertyName, String defaultValue) {
        try {
            Properties p = loadPropertyInstance(fileName);
            String v = p.getProperty(propertyName, defaultValue);

            Map<String, String> retMap = new HashMap<String, String>();
            String[] iA = v.split("(?<!\\\\);");
            for (String i : iA) {
                String[] jA = i.split("(?<!\\\\),");
                if (jA.length == 2) {
                    retMap.put(
                            jA[0].replaceAll("(\\\\)+$", "").replaceAll("\\\\\\,", "\\,").replaceAll("\\\\;", ";").replaceAll("\\\\\\\\", "\\\\"),
                            jA[1].replaceAll("(\\\\)+$", "").replaceAll("\\\\\\,", "\\,").replaceAll("\\\\;", ";").replaceAll("\\\\\\\\", "\\\\"));
                }
            }
            return retMap;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /*
     * 基本
     */

    private static Properties loadPropertyInstance(String fileName) {
        InputStream in = null;
        in = PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName);
        if (in == null) {
            logger.info("抱歉,找不到文件" + fileName);
            return null;
        }
        Properties prop = new Properties();
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static boolean storePropertyInstance(String fileName, Properties p, String comment) {

        URI uri = null;
        OutputStream fos = null;
        try {
            uri = PropertiesUtil.class.getClassLoader().getResource(fileName).toURI();
            fos = new FileOutputStream(new File(uri));
            p.store(fos, comment);
            fos.close();
            return true;
        } catch (URISyntaxException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }


    //public static void main(String[] args) {
    //
    //    //Map map = PropertiesUtil.getAllProperty("system.properties");
    //    //System.out.println(map);
    //    String str = PropertiesUtil.getProperty("system.properties", "geturl");
    //    System.out.println(str);
    //
    //
    //}

}
