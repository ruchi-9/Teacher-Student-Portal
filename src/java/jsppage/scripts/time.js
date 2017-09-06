/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    $("#datepicker").datepicker();
  });
            
function startTime()
{
    var today = new Date();
    var h = today.getHours();
    var m = today.getMinutes();
    var s = today.getSeconds();
    m = checkTime(m);
    s = checkTime(s);
    document.getElementById('txt').innerHTML =
    h + ":" + m + ":" + s;
    var t = setTimeout(startTime, 500);
}
function checkTime(i) 
{
    if (i < 10) {i = "0" + i};  // add zero in front of numbers < 10
    return i;
}
var monthName = new Array('January', 'Febuary', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December');
var hourap = new Array(12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11);
function showTime()
{
    var dateObj = new Date();
    var day = dateObj.getDate(), month = dateObj.getMonth(), year = dateObj.getFullYear(), hour = dateObj.getHours(), minutes = (dateObj.getMinutes()<=9?'0'+dateObj.getMinutes():dateObj.getMinutes());
    var string  = monthName[month]+
    ' '+day+
    ', '+year;
    var timeDiv = document.getElementById('time');
    if(timeDiv !== null) 
    {
        timeDiv.innerHTML = string;
        timeDiv.setAttribute('datetime',year+'-'+(month+1<=9?'0'+(month+1):month+1)+'-'+day+' '+hour+':'+minutes);
    };
};
setInterval(showTime,1000);
 