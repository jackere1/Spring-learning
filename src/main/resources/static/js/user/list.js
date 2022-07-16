'use strict';
var userData = null ;
var table = null ; // DataTables object
/** Processing when loading the screen */
jQuery(function ($){
// DataTables initialization
createDataTables();
/** Search button processing */
$('#btn-search' ).click(function (event) {
// Search
search();
});
});
/** Search processing */
function search() {
// Get the value of form
var formData = $('#user-search-form' ).serialize();
// ajax communication
$.ajax({
type : "GET" ,
url : '/user/get/list' ,
data: formData,
dataType : 'json' ,
contentType: 'application/json; charset=UTF-8' ,
cache : false ,
timeout : 5000,
}).done(function (data) {
// ajax success
console.log(data);
// Put JSON to variable
userData = data;
// Create DataTables
createDataTables();
}).fail(function (jqXHR, textStatus, errorThrown) {
// ajax failed
alert('Search process failed' );
}).always(function () {
// Process to always execute
});
}
/** Create DataTables */
function createDataTables() {
// If DataTables has already been created
if (table !== null ) {
// DataTables discard
table.destroy();
}
// Create DataTables
table = $('#user-list-table' ).DataTable({
//Display data
data: userData,
//Data and column mapping
columns: [
{ data: 'userId' },
{ data: 'userName' },
{
data: 'birthday' ,
render: function ( data, type, row ) {
var date = new Date(data);
var year = date.getFullYear();
var month = date.getMonth() + 1;
var date = date.getDate();
return date + '/' + month + '/' + year;
}
},
{ data: 'age' },
{
data: 'gender' ,
render: function ( data, type, row ) {
var gender = '' ;
if (data === 1) {
gender = 'Male' ;
} else {
gender = 'Female' ;
}
return gender;
}
},
{
data: 'userId' , // URL of user details screen
render: function ( data, type, row ) {
var url = '<a href="/user/detail/' + data + '" class="btn btn-info">Details</a>' ;
return url;
}
},
]
});
}
