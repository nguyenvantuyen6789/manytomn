DELETE FROM `tbl_student_subject`;
DELETE FROM `tbl_student`;
DELETE FROM `tbl_subject`;

INSERT INTO `tbl_subject` (`subject_id`, `subject_name`) VALUES
(1, 'Math'),
(2, 'English');

INSERT INTO `tbl_student` (`student_id`, `student_name`) VALUES
(1, 'Jay'),
(2, 'Anna');

INSERT INTO `tbl_student_subject` (`student_id`, `subject_id`) VALUES
(1, 1),
(1, 2),
(2, 2);
