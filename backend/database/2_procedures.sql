use employeeCRUD;

DELIMITER //
CREATE PROCEDURE getEmployees()
BEGIN
	SELECT * FROM employees;
END //

CREATE PROCEDURE getEmployee(IN param_id INT)
BEGIN
	SELECT *  FROM employees WHERE id = param_id;
END //

CREATE PROCEDURE createEmployee(
    IN param_firstName VARCHAR(100),
    IN param_lastName VARCHAR(100))
BEGIN
	INSERT INTO employees (firstName, lastName) VALUES (param_firstName, param_lastName);
END //


CREATE PROCEDURE updateEmployee(
    IN param_id INT,
    IN param_firstName VARCHAR(100),
    IN param_lastName VARCHAR(100),
    IN param_functions VARCHAR(500))
BEGIN
    CREATE TEMPORARY TABLE updateEmployeeFunctions(function_id INT);
    INSERT INTO updateEmployeeFunctions(function_id) VALUES ('1,2,3,4,5');
	UPDATE employees SET firstName = param_firstName, lastName = param_lastName WHERE Id = param_id;
END //

CREATE PROCEDURE deleteEmployee(
    IN param_id INT)
BEGIN
	DELETE FROM employees WHERE Id = param_id;
END //

DELIMITER ;