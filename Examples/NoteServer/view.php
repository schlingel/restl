<?php

	require_once('db.properties.php');

	$conn = new SQLiteDatabase(DB_NAME, 0666, $err);
	
	if($err) {
		exit($err);
	} else {
		$query = "SELECT title, note FROM " . TABLE_NOTES;
		$result = $conn->arrayQuery($query, SQLITE_ASSOC);
		
		if($result) {
			$result = array("result" => true, "data" => $result);
		} else {
			$result = array("result" => false, "data" => $result);
		}
		
		echo json_encode($result);
	}
?>