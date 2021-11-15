use test;

SET foreign_key_checks = 0;
SELECT @str := CONCAT('TRUNCATE TABLE ', table_schema, '.', table_name, ';')
FROM   information_schema.tables
WHERE  table_type   = 'BASE TABLE'
  AND  table_schema IN ('test');

PREPARE stmt FROM @str;

EXECUTE stmt;

DEALLOCATE PREPARE stmt;

SET foreign_key_checks = 1;