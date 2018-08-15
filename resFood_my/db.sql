--------------------------------------------------------------------
--                创建库，表，约束，过程，用户，权限等脚本
--------------------------------------------------------------------
create database res;

use res;

create table resadmin(
    raid int primary key auto_increment,
    raname varchar(50),
    rapwd  varchar(50)
);

create table resuser(
	userid int  primary key auto_increment,
	username varchar(50),
	pwd varchar(50), 
	email varchar(500)
);
select * from resuser;

--normprice:原价  realprice:现价   description:简介 detail详细的
create table resfood(
	fid int  primary key auto_increment,
	fname varchar(50) ,  
	normprice numeric(8,2),
	realprice numeric(8,2),
	detail varchar(2000),
	fphoto varchar(1000)
);

select * from resfood;
--订单表:   roid:订单号    userid:外键，下单的用户编号    ordertime:下单时间   uname:收货人姓名    deliverytype:送货方式   payment:支付方式, ps附言
create table resorder(
	roid int  primary key auto_increment,
	userid int,
	address varchar(500),
	tel varchar( 100),
	ordertime date,
	deliverytime date,
	ps varchar( 2000),
	status int
);

select roid,userid,address,tel,data_format(ordertime,'%Y-%c-%d %h:%i:%s') as ordertime,
data_format(deliverytime,'%Y-%c-%d %h:%i:%s') as deliverytime,ps,status from resorder where 1=1 ;

select roid,userid,address,tel,date_format(ordertime,'%Y-%c-%d %h:%i:%s') as ordertime,  date_format(deliverytime,'%Y-%c-%d %h:%i:%s') as deliverytime,ps,status from resorder where 1=1  limit 0, 100

select * from resorder;
--订单表的下单人号与用户表中的客户编号有主外键关系. 
alter table resorder
	add constraint fk_resorder
	     foreign key(userid) references resuser(userid);
	     
--dealprice:成交价   roid:订单号   fid:商品号  num:数量
create table resorderitem(
	roiid int  primary key auto_increment,
	roid  int,
	fid   int,
	dealprice numeric(8,2),
	num     int
);
select * from resorderitem;

alter table resorderitem 
   add constraint fk_resorderitem_roid
      foreign key(roid) references resorder( roid);
      
 alter table resorderitem
   add constraint fk_tbl_res_fid
      foreign key( fid ) references resfood( fid);
     

      
create table resorderitemtemp(
	roitid int  primary key auto_increment,
	fid   int,
	num     int,
	quittime date,
	userid int
);
      
      
      
 commit;
 
 select * from resfood where 1=1  order by fid desc

select * from resorder;
select * from resuser;
select roid,fname,dealprice,num,dealprice*num as smallcount from resorderitem  left join resfood on resorderitem.fid=resfood.fid where roid=1


select resfood.fid as fid,fname,ifnull(sum(num),0) as sellcount  from resfood left join resorderitem on resfood.fid=resorderitem.fid  group by resfood.fid,fname  order by sellcount desc limit 0,10; 
