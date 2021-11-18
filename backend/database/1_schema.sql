use employeeCRUD;

create table if not exists employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    firstName VARCHAR(100) NOT NULL,
    lastName VARCHAR(100) NOT NULL
);

create table if not exists functions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

create table if not exists employee_function (
    employee_id INT NOT NULL PRIMARY KEY,
    function_id INT NOT NULL PRIMARY KEY
);
