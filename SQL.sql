/******************************************************/
#Question 1

SELECT first_name, last_name 
FROM lector
WHERE lector_id = (
	SELECT head_id 
	FROM department
	WHERE department_name = '?'
);

/******************************************************/
#Question 2

SELECT degree, COUNT(*)
FROM (
	SELECT *
	FROM (
		SELECT degree, department_name
		FROM (
			SELECT lector.lector_id, degree.degree
			FROM lector
			JOIN degree
			ON lector.degree_id = degree.degree_id
		) AS subquery1
		JOIN lector_department
		ON subquery1.lector_id = lector_department.lector_id
	) AS subquery2
	WHERE department_name = '?'
) AS subquery3
GROUP BY degree;

/******************************************************/
#Question 3

SELECT AVG(salary)
FROM (
	SELECT lector.salary, department_name
	FROM (
		SELECT *
		FROM lector_department
		WHERE department_name = '?'
	) AS subquery1
	JOIN lector
	ON lector.lector_id = subquery1.lector_id
) AS subquery2;

/******************************************************/
#Question 4

SELECT COUNT(*)
FROM lector_department
WHERE department_name = '?';

/******************************************************/
#Question 5

SELECT first_name, last_name
FROM lector
WHERE first_name LIKE '%?%' OR last_name LIKE '%?%';

/******************************************************/