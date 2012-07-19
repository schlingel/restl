<?php
	require_once('db.properties.php');

	$title = $_POST['title'];
	$note = $_POST['note'];

	$conn = new SQLiteDatabase(DB_NAME, 0666, $err);
	
	if($err) {
		exit($err);
	} else {
		$query = "INSERT INTO " . TABLE_NOTES . " (title, note) VALUES ('{$title}', '{$note}')";
		$result = $conn->queryExec($query);
		
		if($result) {
			$result = array("result" => true);
		} else {
			$result = array("result" => false);
		}
		
		echo json_encode($result);
	}
?>