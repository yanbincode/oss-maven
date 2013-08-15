SET DEFINE OFF;

delete from member_info;

insert into member_info (
	member_id,name, age, gender, phone, id_card, creator_id, created_time, modifier_id, modified_time, user_name, pass_word
) values (
	s_member_info.nextval,'admin', 1, 0, 000, 000, 9999, sysdate, 9999, sysdate, 'admin', 'admin'
);

COMMIT;