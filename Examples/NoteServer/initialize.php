<?php

require_once('db.properties.php');

$dbname = DB_NAME;
$mytable = TABLE_NOTES;

$base= new SQLiteDatabase($dbname, 0666, $err);
if ($err)  exit($err);

$query = "CREATE TABLE $mytable(
            title TEXT,
            note TEXT          
            )";
            
$results = $base->queryExec($query);
?>