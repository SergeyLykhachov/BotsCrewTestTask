CREATE TABLE degree(
	degree_id INT AUTO_INCEREMENT,
	degree VARCHAR(19) NOT NULL,
	PRIMARY KEY(degree_id)
);

INSERT INTO degree(degree) VALUES('assistant');

INSERT INTO degree(degree) VALUES('associate professor');

INSERT INTO degree(degree) VALUES('professor');

CREATE TABLE lector(
	lector_id INT AUTO_INCREMENT,
	first_name VARCHAR(30) NOT NULL,
	last_name VARCHAR(40) NOT NULL,
	degree_id INT NOT NULL,
	salary INT NOT NULL,
	PRIMARY KEY(lector_id),
	FOREIGN KEY(degree_id) REFERENCES degree(degree_id) ON DELETE NO ACTION
);

INSERT INTO lector(first_name, last_name, degree_id, salary)
VALUES('Maryna', 'Yurchenko', 2, 15000);

INSERT INTO lector(first_name, last_name, degree_id, salary)
VALUES('Ivan', 'Petrenko', 3, 20000);

INSERT INTO lector(first_name, last_name, degree_id, salary)
VALUES('Petro', 'Ivanov', 2, 15000);

INSERT INTO lector(first_name, last_name, degree_id, salary)
VALUES('Viktor', 'Ivanchenko', 3, 20000);

INSERT INTO lector(first_name, last_name, degree_id, salary)
VALUES('Sofia', 'Sydorenko', 1, 10000);

INSERT INTO lector(first_name, last_name, degree_id, salary)
VALUES('Pavlo', 'Zlepko', 3, 20000);

INSERT INTO lector(first_name, last_name, degree_id, salary)
VALUES('Bohdan', 'Bondarenko', 3, 20000);

INSERT INTO lector(first_name, last_name, degree_id, salary)
VALUES('Natalka', 'Poltavka', 3, 20000);

INSERT INTO lector(first_name, last_name, degree_id, salary)
VALUES('Oksana', 'Ponomarenko', 2, 15000);

INSERT INTO lector(first_name, last_name, degree_id, salary)
VALUES('Iryna', 'Kuzmenko', 3, 20000);

INSERT INTO lector(first_name, last_name, degree_id, salary)
VALUES('Mykola', 'Lysenko', 1, 10000);

INSERT INTO lector(first_name, last_name, degree_id, salary)
VALUES('Vakula', 'Kurylo', 3, 20000);

INSERT INTO department(department_name, head_id)
VALUES('Computer Science', 2);

INSERT INTO department(department_name, head_id)
VALUES('Mathematics', 4);

INSERT INTO department(department_name, head_id)
VALUES('Engineering', 6);

INSERT INTO department(department_name, head_id)
VALUES('Physics', 8);

INSERT INTO lector_department(lector_id, department_name)
VALUES(2, 'Computer Science');

INSERT INTO lector_department(lector_id, department_name)
VALUES(4, 'Computer Science');

INSERT INTO lector_department(lector_id, department_name)
VALUES(8, 'Computer Science');

INSERT INTO lector_department(lector_id, department_name)
VALUES(7, 'Computer Science');

INSERT INTO lector_department(lector_id, department_name)
VALUES(11, 'Computer Science');

INSERT INTO lector_department(lector_id, department_name)
VALUES(4, 'Mathematics');

INSERT INTO lector_department(lector_id, department_name)
VALUES(1, 'Mathematics');

INSERT INTO lector_department(lector_id, department_name)
VALUES(3, 'Mathematics');

INSERT INTO lector_department(lector_id, department_name)
VALUES(7, 'Mathematics');

INSERT INTO lector_department(lector_id, department_name)
VALUES(10, 'Mathematics');

INSERT INTO lector_department(lector_id, department_name)
VALUES(6, 'Engineering');

INSERT INTO lector_department(lector_id, department_name)
VALUES(4, 'Engineering');

INSERT INTO lector_department(lector_id, department_name)
VALUES(8, 'Engineering');

INSERT INTO lector_department(lector_id, department_name)
VALUES(9, 'Engineering');

INSERT INTO lector_department(lector_id, department_name)
VALUES(12, 'Engineering');

INSERT INTO lector_department(lector_id, department_name)
VALUES(8, 'Physics');

INSERT INTO lector_department(lector_id, department_name)
VALUES(4, 'Physics');

INSERT INTO lector_department(lector_id, department_name)
VALUES(1, 'Physics');

INSERT INTO lector_department(lector_id, department_name)
VALUES(5, 'Physics');

INSERT INTO lector_department(lector_id, department_name)
VALUES(10, 'Physics');
