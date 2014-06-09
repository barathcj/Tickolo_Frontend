<?php
mysql_connect("localhost","root","");
mysql_select_db("tickolo_database");

$eventname =   $_POST['name'];
$eventtype =   $_POST['type'];
$eventdate =   $_POST['date'];
$eventsection =   $_POST['section'];
$eventrow =   $_POST['row'];
$eventprice =   $_POST['price'];
$eventnumber =   $_POST['number'];
$eventfvalue =   $_POST['fvalue'];

$sql=mysql_query("insert into tickets (event_name, event_type, section, row, quantity, price, facevalue, event_date) values ('" . $eventname . "','". $eventtype . "','". $eventsection . "','". $eventrow . "','". $eventnumber . "','". $eventprice . "','". $eventfvalue . "','". $eventdate . "')");
//$sql=mysql_query("select * from tickets where event_name = 'ssui class'");
//echo $eventnames;
if (!$sql) {
    $message  = 'Invalid query: ' . mysql_error() . "\n";
    echo $message;
}
print($sql);// this will print the output in json
mysql_close();
?>