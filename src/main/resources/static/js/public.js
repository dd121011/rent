var tokenId = $.cookie('rent_tokenId');
var userId = $.cookie('rent_userId');

var header = {}; //或者 var obj=new Object();
header["tokenId"] = tokenId;
header["userId"] = userId;

var requestBaseUrl = getRootPath();




//js获取项目根路径，如： http://localhost:8083/rent
function getRootPath(){
    //获取当前网址，如： http://localhost:8083/rent/admin/news.action
    var curWwwPath=window.document.location.href;
    //获取主机地址之后的目录，如： rent/admin/news.action
    var pathName=window.document.location.pathname;
    var pos=curWwwPath.indexOf(pathName);
    //获取主机地址，如： http://localhost:8083
    var localhostPaht=curWwwPath.substring(0,pos);
    //获取带"/"的项目名，如：/rent
    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);
    return(localhostPaht+projectName);
}

$.fn.serializeObject = function()
{
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [o[this.name]];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};

//----------------------------------------------------------
//    功能：根据身份证号获得出生日期
//  参数：身份证号 psidno
//    返回值：
//    出生日期
//----------------------------------------------------------
function GetBirthday(psidno){
    var birthdayno,birthdaytemp
    if(psidno.length==18){
        birthdayno=psidno.substring(6,14)
    }else if(psidno.length==15){
        birthdaytemp=psidno.substring(6,12)
        birthdayno="19"+birthdaytemp
    }else{
        alert("错误的身份证号码，请核对！")
        return false
    }
    var birthday=birthdayno.substring(0,4)+"-"+birthdayno.substring(4,6)+"-"+birthdayno.substring(6,8)
    return birthday
}

//----------------------------------------------------------
//    功能：根据身份证号获得性别
//  参数：身份证号 psidno
//    返回值：
//    性别
//----------------------------------------------------------
function Getsex(psidno){
    var sexno,sex
    if(psidno.length==18){
        sexno=psidno.substring(16,17)
    }else if(psidno.length==15){
        sexno=psidno.substring(14,15)
    }else{
        alert("错误的身份证号码，请核对！")
        return false
    }
    var tempid=sexno%2;
    if(tempid==0){
        // sex='F'
        sex='2'
    }else{
        // sex='M'
        sex='1'
    }
    return sex
}