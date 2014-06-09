<?php
mysql_connect("localhost","root","");
mysql_select_db("tickolo_database");
$eventtype =   $_POST['eventtype'];
$sql=mysql_query("select * from tickets where event_type = '" . $eventtype . "'");
//$sql=mysql_query("select * from tickets where event_name = 'ssui class'");
//echo $eventnames;
while($row=mysql_fetch_assoc($sql)){
	$output[]=$row;
}
print(json_encode($output));// this will print the output in json
mysql_close();
?>