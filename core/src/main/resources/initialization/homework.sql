select * from medical_card
join illness i on medical_card.id = i.medical_card_id;

create view homework as select medical_card.id, client_status, med_status, registry_dt, comment from address, medical_card where address.id = medical_card.id;

select * from homework;