String.prototype.replaceAll = function(reallyDo, replaceWith, ignoreCase) {
    if (!RegExp.prototype.isPrototypeOf(reallyDo)) {
        return this.replace(new RegExp(reallyDo, (ignoreCase ? "gi": "g")), replaceWith);
    } else {
        return this.replace(reallyDo, replaceWith);
    }
};

layui.define('jquery', function(exports){
    'use strict';

    var $ = layui.$;

    var utils = {
        cutString:cutString,
        md5:md5,
        fromJson:fromJson,
        toJson:toJson,
        unParam:unParam,
        getUrlParam:getUrlParam,
        resetUrlParam:resetUrlParam,
        unique:unique,
        removeInArray:removeInArray,
        nullToString:nullToString,
        dateFormat:dateFormat,
        dateToLong:dateToLong,
        dateToString:dateToString,
        isEmail:isEmail,
        isMobile:isMobile,
        isIdCard:isIdCard,
        checkPwdLevel:checkPwdLevel,
        checkClient:checkClient,
        setData:setData,
        getData:getData
    }

    function setData(body,data) {
        var inputs = body.find("input[name],select[name],textarea[name],checkbox[name]");
        if(inputs && inputs.length){
            $.each(inputs,function(i,v){
                var input_type = $(v).attr("type");
                var val = data[$(v).attr("name")];
                if(input_type == "radio"){
                    if($(v).val() == val){
                        $(v).prop("checked","checked");
                    }
                }else if(input_type  == "checkbox"){
                    if($(v).val() == val) {
                        $(v).prop("checked","checked");
                    }
                }else if(input_type == "buttton"){

                }else{
                    $(v).val(val)
                }
            });
        }
    }

   function getData() {
        var data = {};
        var inputs = this.find("input[name],select[name],textarea[name],checkbox[name]");
        if(inputs && inputs.length){
            $.each(inputs,function(i,v){
                var input_type = $(v).attr("type");
                if(input_type == "radio" && !v.checked){
                }else if(input_type  == "checkbox"){
                    if(v.checked){
                        if(!data[$(v).attr("name")]){
                            data[$(v).attr("name")] = [];
                        }
                        data[$(v).attr("name")].push($(v).val());
                    }
                }else if(input_type == "buttton"){

                }else{
                    $(v).attr("name")?data[$(v).attr("name")] = $(v).val():'';
                }
            });
        }
        return data;
    }

       function cutString(str,len) {
        if(str == null || str == "") return str;
        return str.length > len?(str.substring(0,len)+"..."):str;
    }

    function checkClient(){
        var userAgentInfo = navigator.userAgent;
        var Agents = new Array("Android", "iPhone", "SymbianOS", "Windows Phone", "iPad", "iPod");
        var flag = false;
        for (var v = 0; v < Agents.length; v++) {
            if (userAgentInfo.indexOf(Agents[v]) > 0) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    function unique(arr) {
        var result = [], hash = {};
        for (var i = 0, elem; (elem = arr[i]) != null; i++) {
            if (!hash[elem]) {
                result.push(elem);
                hash[elem] = true;
            }
        }
        return result;
    }

    function dateFormat(date){
        var d = new Date(date);
        var year = d.getFullYear();
        var month = d.getMonth()+1;
        if(month < 10){
            month = "0" +month;
        }
        var day = d.getDate();
        if(day < 10){
            day = "0" +day;
        }
        var hour = d.getHours();
        if(hour < 10){
            hour = "0" +hour;
        }
        var minutes = d.getMinutes();
        if(minutes < 10){
            minutes = "0" +minutes;
        }
        var second = d.getSeconds();
        if(second < 10){
            second = "0" +second;
        }
        date= year+"-"+month+"-"+day+" "+hour+":"+minutes +":"+second;
        return date;
    }


    function dateToString(dt) {
        var month = (dt.getMonth() + 1) + "";
        var date = dt.getDate() + "";
        var hour = dt.getHours() + "";
        var minutes = dt.getMinutes() + "";
        var second = dt.getSeconds() + "";
        return formatDateYear(dt.getFullYear(), month, date, hour, minutes, second);
    }

    function formatDate(month, date, hour, minutes, second){
        if((""+month).length < 2){
            month = "0" + month;
        }
        if((""+date).length < 2){
            date = "0" + date;
        }
        if((""+hour).length < 2){
            hour = "0" + hour;
        }
        if((""+minutes).length < 2){
            minutes = "0" + minutes;
        }
        if(typeof(second) != "undefined" ){
            if((""+second).length < 2){
                second = "0" + second;
            }
            return month+"."+date+" "+hour+":"+minutes+":"+second;
        }else{
            return month+"."+date+" "+hour+":"+minutes;
        }
    }

    function formatDateYear(year,month, date, hour, minutes, second){
        if(year.length <2){
            year = "0"+year;
        }
        return year+"."+formatDate(month, date, hour, minutes, second);
    }
    function dateToLong(str) {
        return new Date(str).getTime();
    }

    function fromJson(str) {
        if(str && typeof str == "string"){
            str = JSON.parse(str);
        }
        return str;
    }

    function toJson(obj) {
        if(obj && typeof obj == "object"){
            obj = JSON.stringify(obj);
        }
        return obj;
    }


    function nullToString(obj){
        if(!obj)return;
        var o;
        if(obj.length){
            o = [];
            for (var i = 0; i < obj.length; i++) {
                var oob = dealObjectNull(obj[i]);
                o.push(oob);
            }
        }else{
            o = dealObjectNull(obj);
        }
        return o;
    }

    function dealObjectNull(obj){
        if(!obj)return;
        var o = {};
        for(var k in obj){
            o[k] = obj[k] == null?"":obj[k];
        }
        return o;
    }

    function removeInArray(item,arr) {
        var len = arr.length;
        if(item && len){
            for (var i = 0; i < len; i++){
                if(item == arr[i]){
                    arr.splice(i,1);
                }
            }
        }
    }

    function getUrlParam(name){
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null)
            return decodeURIComponent(r[2]);
        return null;
    }

    function resetUrlParam(k,v,link){
        if(!link){
            link = window.location.href;
        }
        if(typeof(k) == "string"){
            var s = "&"+k+"=";
            var ss = "?"+k+"=";
            var c = "";
            if(link.indexOf("#") > 0){
                c = "#"+link.split("#")[1];
                link = link.split("#")[0];
            }
            if(link.indexOf(s) > 0){
                var b = link.split(s)[0];

                var a = "";
                if(link.split(s)[1].indexOf("&") > 0){
                    var at = link.split(s)[1];
                    a = at.substring(at.indexOf("&"));
                }
                if(v == null){
                    return b + a + c;
                }else{
                    return b + s + v + a + c;
                }
            }else if(link.indexOf(ss) > 0){
                var b = link.split(ss)[0];
                var a = "";
                if(link.split(ss)[1].indexOf("&") > 0){
                    var at = link.split(ss)[1];
                    a = at.substring(at.indexOf("&"));
                }
                if(v == null){
                    return b + a.replace("&", "?") + c;
                }else{
                    return b + ss + v + a + c;
                }
            }else{
                if(v == null){
                    return link + c;
                }else{
                    if(link.indexOf("?") > 0){
                        return link + s + v + c;
                    }else{
                        return link + ss + v + c;
                    }
                }
            }
        }else if(typeof(k) == "object"){
            for ( var p in k ){ // 方法
                link = resetUrlParm(p,k[p],link);
            }
            return link;
        }
    }

    function unParam(str){
        var rs = {};
        str.split('&').forEach(function(param){
            if(param.indexOf("[") > 0 && param.indexOf("[") < param.indexOf("=")){
                var keyFalse = param.substring(0,param.indexOf("="));
                var keyTrue = param.substring(0,param.indexOf("["));
                var val = param.substring(param.indexOf("=") + 1);
                dealParam(keyFalse,keyTrue,val,rs);
            }else{
                param = param.split('=');
                rs[param[0]] = param[1];
            }
        });
        return rs;
    }
    function dealParam(str2,key,val,rs){
        var k = key;
        var t = rs;
        var a = str2.substring(str2.indexOf("[") + 1,str2.indexOf("]"));
        var r = str2.substring(str2.indexOf("]") + 1);
        if(a && !$.isNumeric(a)){
            if($.isArray(rs)){
                if(rs.length <= key){
                    rs.push({});
                }
                k = a;
                t = rs[key];
            }else{
                if(!rs[key]){
                    rs[key] = {};
                }
                k = a;
                t = rs[key];
            }
        }else{
            if($.isArray(rs)){
                if(!rs[key]){
                    rs[key] = [];
                }
                k = a;
                t = rs[key];
            }else{
                if(!rs[key]){
                    rs[key] = [];
                }
                k = a;
                t = rs[key];
            }
        }
        if(r.indexOf("[") == 0){
            dealParam(r,k,val,t);
        }else{
            if(a && !$.isNumeric(a)){
                t[a] = val;
            }else{
                if(!a){
                    t.push(val);
                }else{
                    t[a] = val;
                }
            }
        }
    }

    function isEmail(str){
        var reg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
        return reg.test(str);
    }

    function isMobile(tel){
        var reg = /^1[3-9]\d{9}$/;
        return reg.test(tel);
    }

    function isIdCard(str){
        var reg = /^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])((\d{4})|\d{3}[A-Z])$/;
        return reg.test(str);
    }

    function checkPwdLevel(val){
        var lv = 0;
        if(val.match(/[a-z]/g)){lv++;}
        if(val.match(/[0-9]/g)){lv++;}
        if(val.match(/(.[^a-z0-9])/g)){lv++;}
        if(val.length < 6){lv=0;}
        if(lv > 3){lv=3;}
        return lv;
    }

    function md5(sMessage) {
        function RotateLeft(lValue, iShiftBits) {
            return (lValue << iShiftBits) | (lValue >>> (32 - iShiftBits));
        }

        function AddUnsigned(lX, lY) {
            var lX4, lY4, lX8, lY8, lResult;
            lX8 = (lX & 0x80000000);
            lY8 = (lY & 0x80000000);
            lX4 = (lX & 0x40000000);
            lY4 = (lY & 0x40000000);
            lResult = (lX & 0x3FFFFFFF) + (lY & 0x3FFFFFFF);
            if (lX4 & lY4)
                return (lResult ^ 0x80000000 ^ lX8 ^ lY8);
            if (lX4 | lY4) {
                if (lResult & 0x40000000)
                    return (lResult ^ 0xC0000000 ^ lX8 ^ lY8);
                else
                    return (lResult ^ 0x40000000 ^ lX8 ^ lY8);
            } else
                return (lResult ^ lX8 ^ lY8);
        }

        function F(x, y, z) {
            return (x & y) | ((~x) & z);
        }

        function G(x, y, z) {
            return (x & z) | (y & (~z));
        }

        function H(x, y, z) {
            return (x ^ y ^ z);
        }

        function I(x, y, z) {
            return (y ^ (x | (~z)));
        }

        function FF(a, b, c, d, x, s, ac) {
            a = AddUnsigned(a, AddUnsigned(AddUnsigned(F(b, c, d), x), ac));
            return AddUnsigned(RotateLeft(a, s), b);
        }

        function GG(a, b, c, d, x, s, ac) {
            a = AddUnsigned(a, AddUnsigned(AddUnsigned(G(b, c, d), x), ac));
            return AddUnsigned(RotateLeft(a, s), b);
        }

        function HH(a, b, c, d, x, s, ac) {
            a = AddUnsigned(a, AddUnsigned(AddUnsigned(H(b, c, d), x), ac));
            return AddUnsigned(RotateLeft(a, s), b);
        }

        function II(a, b, c, d, x, s, ac) {
            a = AddUnsigned(a, AddUnsigned(AddUnsigned(I(b, c, d), x), ac));
            return AddUnsigned(RotateLeft(a, s), b);
        }

        function ConvertToWordArray(sMessage) {
            var lWordCount;
            var lMessageLength = sMessage.length;
            var lNumberOfWords_temp1 = lMessageLength + 8;
            var lNumberOfWords_temp2 = (lNumberOfWords_temp1 - (lNumberOfWords_temp1 % 64)) / 64;
            var lNumberOfWords = (lNumberOfWords_temp2 + 1) * 16;
            var lWordArray = Array(lNumberOfWords - 1);
            var lBytePosition = 0;
            var lByteCount = 0;
            while (lByteCount < lMessageLength) {
                lWordCount = (lByteCount - (lByteCount % 4)) / 4;
                lBytePosition = (lByteCount % 4) * 8;
                lWordArray[lWordCount] = (lWordArray[lWordCount] | (sMessage
                    .charCodeAt(lByteCount) << lBytePosition));
                lByteCount++;
            }
            lWordCount = (lByteCount - (lByteCount % 4)) / 4;
            lBytePosition = (lByteCount % 4) * 8;
            lWordArray[lWordCount] = lWordArray[lWordCount]
                | (0x80 << lBytePosition);
            lWordArray[lNumberOfWords - 2] = lMessageLength << 3;
            lWordArray[lNumberOfWords - 1] = lMessageLength >>> 29;
            return lWordArray;
        }

        function WordToHex(lValue) {
            var WordToHexValue = "", WordToHexValue_temp = "", lByte, lCount;
            for (lCount = 0; lCount <= 3; lCount++) {
                lByte = (lValue >>> (lCount * 8)) & 255;
                WordToHexValue_temp = "0" + lByte.toString(16);
                WordToHexValue = WordToHexValue
                    + WordToHexValue_temp.substr(
                        WordToHexValue_temp.length - 2, 2);
            }
            return WordToHexValue;
        }
        var x = Array();
        var k, AA, BB, CC, DD, a, b, c, d;
        var S11 = 7, S12 = 12, S13 = 17, S14 = 22;
        var S21 = 5, S22 = 9, S23 = 14, S24 = 20;
        var S31 = 4, S32 = 11, S33 = 16, S34 = 23;
        var S41 = 6, S42 = 10, S43 = 15, S44 = 21;
        x = ConvertToWordArray(sMessage);
        a = 0x67452301;
        b = 0xEFCDAB89;
        c = 0x98BADCFE;
        d = 0x10325476;
        for (k = 0; k < x.length; k += 16) {
            AA = a;
            BB = b;
            CC = c;
            DD = d;
            a = FF(a, b, c, d, x[k + 0], S11, 0xD76AA478);
            d = FF(d, a, b, c, x[k + 1], S12, 0xE8C7B756);
            c = FF(c, d, a, b, x[k + 2], S13, 0x242070DB);
            b = FF(b, c, d, a, x[k + 3], S14, 0xC1BDCEEE);
            a = FF(a, b, c, d, x[k + 4], S11, 0xF57C0FAF);
            d = FF(d, a, b, c, x[k + 5], S12, 0x4787C62A);
            c = FF(c, d, a, b, x[k + 6], S13, 0xA8304613);
            b = FF(b, c, d, a, x[k + 7], S14, 0xFD469501);
            a = FF(a, b, c, d, x[k + 8], S11, 0x698098D8);
            d = FF(d, a, b, c, x[k + 9], S12, 0x8B44F7AF);
            c = FF(c, d, a, b, x[k + 10], S13, 0xFFFF5BB1);
            b = FF(b, c, d, a, x[k + 11], S14, 0x895CD7BE);
            a = FF(a, b, c, d, x[k + 12], S11, 0x6B901122);
            d = FF(d, a, b, c, x[k + 13], S12, 0xFD987193);
            c = FF(c, d, a, b, x[k + 14], S13, 0xA679438E);
            b = FF(b, c, d, a, x[k + 15], S14, 0x49B40821);
            a = GG(a, b, c, d, x[k + 1], S21, 0xF61E2562);
            d = GG(d, a, b, c, x[k + 6], S22, 0xC040B340);
            c = GG(c, d, a, b, x[k + 11], S23, 0x265E5A51);
            b = GG(b, c, d, a, x[k + 0], S24, 0xE9B6C7AA);
            a = GG(a, b, c, d, x[k + 5], S21, 0xD62F105D);
            d = GG(d, a, b, c, x[k + 10], S22, 0x2441453);
            c = GG(c, d, a, b, x[k + 15], S23, 0xD8A1E681);
            b = GG(b, c, d, a, x[k + 4], S24, 0xE7D3FBC8);
            a = GG(a, b, c, d, x[k + 9], S21, 0x21E1CDE6);
            d = GG(d, a, b, c, x[k + 14], S22, 0xC33707D6);
            c = GG(c, d, a, b, x[k + 3], S23, 0xF4D50D87);
            b = GG(b, c, d, a, x[k + 8], S24, 0x455A14ED);
            a = GG(a, b, c, d, x[k + 13], S21, 0xA9E3E905);
            d = GG(d, a, b, c, x[k + 2], S22, 0xFCEFA3F8);
            c = GG(c, d, a, b, x[k + 7], S23, 0x676F02D9);
            b = GG(b, c, d, a, x[k + 12], S24, 0x8D2A4C8A);
            a = HH(a, b, c, d, x[k + 5], S31, 0xFFFA3942);
            d = HH(d, a, b, c, x[k + 8], S32, 0x8771F681);
            c = HH(c, d, a, b, x[k + 11], S33, 0x6D9D6122);
            b = HH(b, c, d, a, x[k + 14], S34, 0xFDE5380C);
            a = HH(a, b, c, d, x[k + 1], S31, 0xA4BEEA44);
            d = HH(d, a, b, c, x[k + 4], S32, 0x4BDECFA9);
            c = HH(c, d, a, b, x[k + 7], S33, 0xF6BB4B60);
            b = HH(b, c, d, a, x[k + 10], S34, 0xBEBFBC70);
            a = HH(a, b, c, d, x[k + 13], S31, 0x289B7EC6);
            d = HH(d, a, b, c, x[k + 0], S32, 0xEAA127FA);
            c = HH(c, d, a, b, x[k + 3], S33, 0xD4EF3085);
            b = HH(b, c, d, a, x[k + 6], S34, 0x4881D05);
            a = HH(a, b, c, d, x[k + 9], S31, 0xD9D4D039);
            d = HH(d, a, b, c, x[k + 12], S32, 0xE6DB99E5);
            c = HH(c, d, a, b, x[k + 15], S33, 0x1FA27CF8);
            b = HH(b, c, d, a, x[k + 2], S34, 0xC4AC5665);
            a = II(a, b, c, d, x[k + 0], S41, 0xF4292244);
            d = II(d, a, b, c, x[k + 7], S42, 0x432AFF97);
            c = II(c, d, a, b, x[k + 14], S43, 0xAB9423A7);
            b = II(b, c, d, a, x[k + 5], S44, 0xFC93A039);
            a = II(a, b, c, d, x[k + 12], S41, 0x655B59C3);
            d = II(d, a, b, c, x[k + 3], S42, 0x8F0CCC92);
            c = II(c, d, a, b, x[k + 10], S43, 0xFFEFF47D);
            b = II(b, c, d, a, x[k + 1], S44, 0x85845DD1);
            a = II(a, b, c, d, x[k + 8], S41, 0x6FA87E4F);
            d = II(d, a, b, c, x[k + 15], S42, 0xFE2CE6E0);
            c = II(c, d, a, b, x[k + 6], S43, 0xA3014314);
            b = II(b, c, d, a, x[k + 13], S44, 0x4E0811A1);
            a = II(a, b, c, d, x[k + 4], S41, 0xF7537E82);
            d = II(d, a, b, c, x[k + 11], S42, 0xBD3AF235);
            c = II(c, d, a, b, x[k + 2], S43, 0x2AD7D2BB);
            b = II(b, c, d, a, x[k + 9], S44, 0xEB86D391);
            a = AddUnsigned(a, AA);
            b = AddUnsigned(b, BB);
            c = AddUnsigned(c, CC);
            d = AddUnsigned(d, DD);
        }
        var temp = WordToHex(a) + WordToHex(b) + WordToHex(c) + WordToHex(d);
        return temp.toLowerCase();
    }

    exports('utils', utils);


});

