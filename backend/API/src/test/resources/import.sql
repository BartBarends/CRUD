create table if not exists employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL);

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
    IN param_lastName VARCHAR(100))
BEGIN
UPDATE employees SET firstName = param_firstName, lastName = param_lastName WHERE Id = param_id;
END //

CREATE PROCEDURE deleteEmployee(
    IN param_id INT)
BEGIN
DELETE FROM employees WHERE Id = param_id;
END //

INSERT INTO employees(id, firstName, lastName) VALUES (1, "Bart", "Barends");
INSERT INTO employees(id, firstName, lastName) VALUES (2, "Ard-Jan", "van Etten");
INSERT INTO employees(id, firstName, lastName) VALUES (3, "Tim", "Meuwsen");
