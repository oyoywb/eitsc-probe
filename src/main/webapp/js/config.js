var host    = 'http://192.168.0.115:8080/'
var schoolCode = getQueryVariable('orgcode');
var schoolName = getQueryVariable('orgname');
//var schoolCode = 111111;   //测试变量(教育局)
function getQueryVariable(variable)//获取url参数
	{
       var query = window.location.search.substring(1);
       var vars = query.split("&");
       for (var i=0;i<vars.length;i++) {
               var pair = vars[i].split("=");
               if(pair[0] == variable){return pair[1];}
       }
       return(false);
}
function PageValue(queryName) {
        var query = decodeURI(window.location.hash.substring(1));
        var vars = query.split("!");
        for (var i = 0; i < vars.length; i++) {
                var pair = vars[i].split("=");
                if (pair[0] == queryName) { return pair[1]; }
        }
        return null;
}
//获取时间
function getTimes(str){
        // var _d = new Date();
        // var _s = _d.getFullYear() + '-' + (_d.getMonth() + 1) + '-' + _d.getDate();
        // if (str) {
        //     _s += ' ' + _d.getHours() + ':' + _d.getMinutes() + ':' + _d.getSeconds();
        // }
        var date=new Date();

        var year=date.getFullYear();
        var month=date.getMonth()+1;
        var day=date.getDate();

        var hour=date.getHours();
        var minute=date.getMinutes();
        var second=date.getSeconds();

        //这样写显示时间在1~9会挤占空间；所以要在1~9的数字前补零;
        if(month<10){
            month = '0'+month
        }
        if(day<10){
            day = '0'+day
        }
        if (hour<10) {
            hour='0'+hour;
        }
        if (minute<10) {
            minute='0'+minute;
        }
        if (second<10) {
            second='0'+second;
        }
        var time=year+'-'+month+'-'+day;
        if(str == 1){
            var time=year+'-'+month+'-'+day+'-'+hour+':'+minute+':'+second
        }else if(str == 2){
            var time=year+'-'+month
        }else if(str == 3){

        }
        return time;
    };
