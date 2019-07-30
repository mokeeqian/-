/*
-- Query: select * from tb_question
LIMIT 0, 500

-- Date: 2019-07-25 08:32
*/
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (1,'2/3',5,5,'指出下面语句没有编译错误的是:','long n = 999999999999;','int n = 999999999999L;','long n = 999999999999L; ','double n = 999999999999;');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (2,'1/2',5,4,'下列关于数组的声明错误的是:','int[] arry = new int[100];','int[3] arry = {1,2,3};','int[] arry = new int[]{1,2,3};','int[][] arry = new int[3][];');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (3,'0/1/2',5,3,'语句System.out.println(1+2+\"java\"+3+4)输出的结果是:','3java34','12java34','3java7','12java7');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (4,'1',5,6,'Java语言中int类型标示整数的最大范围是:','-2147483647~ 2147483647 ','<T>-2147483648 ~2147483647 ','-32767~32767','-32768~32767');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (5,'1',5,7,'Java语言中字符串“学Java”所占的内存空间是:','6个字节','7个字节','10个字节','11个字节');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (6,'1',5,8,'对JDK描述错误的是:','运行Java程序只要有JRE环境就可以，不一定需要全部JDK的功能','JDK中包括JVM、核心类库、Java开发工具(java，javac)等','JDK本身也是平台无关的，不同的操作系统安装的JDK是一样的','JDK的全称是 Java Development Kit');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (7,'1',5,9,'对CLASSPATH描述正确的是:','设置CLASSPATH用于指示操作系统找到JDK命令，如java或javac','CLASSPATH设置不对，可能会导致java命令失败，但不会导致javac失败','CLASSPATH的设置中可以包含目录路径但不可以包含文件路径','java命令可以通过 -cp 参数指定类路径');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (8,'1',5,10,'下面Java语言方法的说法错误的是:','方法调用时参数类型必须符合方法的定义','方法只能声明一个返回值','如果方法没有返回值必须声明返回为void','如果方法定义为返回void，则方法中不能出现return语句');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (9,'1',5,2,'下面不属于Java语言特点的是:','平台无关','面向对象','支持指针操作','垃圾回收机制');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (10,'1',5,3,'关于数组的说法正确的是:','数组类型属于基本类型','数组创建后，其的长度可以改变','int[]类型的变量可以直接赋值给long[]类型的变量','数组的长度必须在创建是指定');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (11,'1',5,4,'机动车在雾天行驶时，应当开启雾灯和______。','危险报警闪光灯','转向灯','远光灯','近光灯');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (12,'1',5,5,'机动车驾驶人有饮酒后或者醉酒后驾驶机动车违法行为的，一次记______分。','2','3','6','12');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (13,'1',5,6,'机动车与机动车发生财产损失事故，当事人对事实及成因无争议的，可自行协商处理损害赔偿事宜，填写______。','知情人证明','道路交通事故损害赔偿协议书','当事人的报告','赔偿费用发票');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (14,'1',5,7,'牵引故障机动车时，牵引车和被牵引车均应当开启______。','前大灯','左转向灯','右转向灯','危险报警闪光灯');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (15,'1',5,8,'机动车遇行人正在通过人行横道时，应当______。　  ','停车让行','绕行通过','持续鸣喇叭通过','提前加速通过');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (16,'1',5,9,'已注册登记的机动车达到国家规定的强制报废标准的，机动车所有人应当将车辆在报废期满前______。','卖给附近废品收购站','交售给规定的机动车回收企业','自行处理','大修后转售');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (17,'1',5,10,'机动车驾驶人初次申领机动车驾驶证后的______为实习期。','3个月','6个月','12个月','24个月');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (18,'1',5,1,'将机动车交由未取得机动车驾驶证的人或机动车驾驶证被吊销、暂扣的人驾驶的, 由公安交通管理部门处200元以上2000元以下罚款，还可以并处______。',' 15 日以下拘留',' 吊销驾驶证',' 扣留车辆',' 扣留行驶证');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (19,'1',5,2,'在没有道路中心线的道路上，遇后车发出超车信号时，前车应当______。','保持原有状态行驶','加速行驶','迅速停车让行','在条件许可的情况下，降低速度、靠右让路');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (20,'1',5,3,'机动车在设有最高限速标志的道路上行驶时，______。','不得超过标明的最高时速','允许超过标明最高时速的10%','可以超过车辆的最高设计时速','必须按规定的最高车速行驶');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (21,'1',5,4,'机动车交通事故责任强制保险的保险期间为______。','1年','2年','3年','4年');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (22,'1',5,5,'机动车通过没有交通信号灯、交通标志、交通标线或者交通警察指挥的交叉路口时，应当______。','迅速通过','减速慢行','适当加速','保持行驶速度');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (23,'1',5,6,'机动车行驶超过规定时速５０％的，依法______。','吊销行驶证','拘留驾驶人','吊销驾驶证','扣留机动车驾驶证');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (24,'1',5,7,'在我国境内道路上行驶的机动车______，应当依照道路交通安全法的规定投保机动车交通事故责任强制保险。','所有人和管理人','担保人','乘坐人','维修人');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (25,'1',5,8,'已经登记注册的机动车有______变动，不必到车管所办理相应的登记手续。','车辆所有人发生变化','改变车身颜色','更换发动机','小型汽车加装前后防撞装置');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (26,'1',5,9,'饮酒后驾驶机动车的，处暂扣______，并处200元以上500元以下罚款。','机动车','行驶证','１个月以上３个月以下驾驶证','3个月以上驾驶证,由民警决定');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (27,'1',5,1,'机动车驾驶人在实习期内不得驾驶______。','小型汽车','营运出租车','自动挡汽车','三轮汽车');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (28,'1',5,2,'会车中道路一侧有障碍的，双方车辆应做到______先行。','无障碍一方让对方','有障碍的一方让对方','速度慢的让速度快的','速度快的让速度慢的');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (29,'1',5,3,'高速公路没有限速标志的，最高时速不得超过______。','90公里','100公里','120公里','130公里');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (30,'1',5,4,'机动车驾驶人因交通肇事______，处7年以上15年以下有期徒刑。','发生重大事故的','致人重伤的','公私财产遭受重大损失的','逃逸致人死亡的');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (31,'1',5,5,'对未按照规定投保______的机动车所有人、管理人，公安交通管理部门可以按规定投保最低责任限额应缴纳的保险费的2倍罚款。','机动车交通事故责任强制保险','机动车车身险','机动车盗抢险','机动车第三者责任险');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (32,'1',5,6,'机动车驾驶人在一个记分周期内累积记分达到12分的，接受教育后，车辆管理所应当在二十日内对其进行______。','科目一理论考试','罚款并吊销驾驶证 ','科目二考试 ','科目三考试');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (33,'1',5,7,'驾驶人连续驾驶机动车超过______的，应当停车休息不得少于20分钟。','4小时','6小时','7小时','8小时');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (34,'1',5,8,'警车、消防车、救护车、工程救险车执行紧急任务时，其他车辆______。','可加速穿行','可谨慎超越','视情让行','应当让行');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (35,'1',5,9,'在道路上发生交通事故，仅造成轻微财产损失，并且基本事实清楚的，当事人______。','不得撤离现场','应当迅速报警','应当先撤离现场再进行协商处理','应当将车停在原地协商赔偿');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (36,'1',5,1,'机动车在高速公路上行驶，______。','可在匝道、加速车道、减速车道上超车','不准倒车、逆行、穿越中央分隔带掉头','非紧急情况时可在应急车道行驶','可以试车或学习驾驶');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (37,'1',5,2,'机动车在道路上发生故障，难以移动的，首先应当持续开启危险报警闪光灯，______。','抓紧排除故障','向过往车辆求救','并在来车方向设置警告标志','与维修厂联系');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (38,'1',5,3,'未取得机动车驾驶证或机动车驾驶证被暂扣、吊销后驾驶机动车的, 由公安交通管理部门处______，还可以并处15日以下拘留。','扣留机动车','200元以上2000元以下罚款','2000元以上罚款，由民警决定','扣留行使证');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (39,'1',5,4,'机动车在道路上临时停车时，应当______，机动车驾驶人不得离车,上下人员或者装卸物品后,立即驶离。','在非机动车道停放','紧靠道路右侧停放','紧靠道路左侧停放','在人行道上停放');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (40,'1',5,5,'发生道路交通事故造成人员死亡、受伤的，当事人应当保护现场并______。','查明事故原因','与对方协商损害赔偿','立即报警','找现场证人');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (41,'1',5,6,'没有划分机动车道、非机动车道和人行道的道路，机动车______。 ','在道路两边通行','在道路中间通行','实行分道通行','可随意通行');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (42,'1',5,7,'机动车在遇有前方机动车缓慢行驶时，应当______。','从前方车辆两侧穿插','停车等候','从前方车辆两侧超越','依次排队行驶');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (43,'1',5,8,'机动车通过没有交通信号或没有管理人员的铁道路口时，应______。','按原来车速行驶','减速或停车观察','加速尽快通过','紧随前车行驶');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (44,'1',5,1,'年满70周岁，只能持有______准驾的驾驶证','C2','E','D','F');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (45,'1',5,9,'机动车向左转弯、向左变更车道、超车前、驶离停车地点及掉头时，______。','可以在转向的同时开启转向灯','可以先转向后开启转向灯','只要没有其他车辆可以不开转向灯','应当提前开启左转向灯');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (46,'1',5,8,'机动车驾驶人因服兵役、出国（境）等原因，无法在规定时间内办理驾驶证期满换证、提交身体条件证明的，可以向机动车驾驶证核发地车辆管理所申请延期办理。延期期限最长不超过______。','三年','二年','一年','半年');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (47,'1',5,7,'机动车驾驶人应当于驾驶证有效期满前______内，向核发地车辆管理所申请换证。','90日','120日','180日','240日');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (48,'1',5,6,'机动车驾驶人累计记分达到12分，拒不参加公安机关交通管理部门通知的学习，也不接受考试的，由公安机关交通管理部门______。','公告其驾驶证停止使用','扣留其驾驶证','吊销其驾驶证','对其加倍处以罚款');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (49,'1',5,5,'多次发生道路交通安全违法行为、道路交通事故，或者发生重大道路交通事故的被保险车，保险公司应当______保险费率的幅度。','保持','降低','提高','适当调整');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (50,'1',5,4,'机动车驾驶证有效期分为______、10年和长期。','1年','2年','5年','6年');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (51,'1',5,3,'已达到报废标准的机动车______上道路行驶。','允许临时','不得','经维修后可以','缴管理费后可以');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (52,'1',5,2,'在车道减少的路段、路口，机动车应当______。','借道超车','依次交替通行','加速通过','抢道行驶');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (53,'1',5,1,'驾驶人______，承担交通事故全部责任。','与行人发生事故的','与非机动发生事故的','故意破坏、伪造现场、毁灭证据的','与对方机动车发生事故的');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (54,'1',5,9,'机动车行经没有交通信号的道路，遇行人横过道路时，应当______。','鸣喇叭，让行人快走','加速行驶','减速或停车避让 ','绕行通过');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (55,'1',5,7,'机动车遇有前方机动车停车排队等候时，应当______。','从前方车辆两侧穿插','从前方车辆左侧超越','从前方车辆右侧超越','依次排队');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (56,'1',5,6,'造成交通事故后逃逸，尚不构成犯罪的,公安交通管理部门除按照规定罚款外，还可以并处______。',' 15日以下拘留',' 吊销驾驶证',' 扣留车辆',' 扣留行驶证');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (57,'1',5,5,'机动车在高速公路上行驶，______。','时速可超过120公里','可在应急车道内行驶','不准在匝道、加速车道或者减速车道上超车','可停车休息');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (58,'1',5,4,'机动车从匝道驶入高速公路，应当先在加速车道内提速，并开启______，在不妨碍已在高速公路内的机动车正常行驶的情况下驶入车道。','左转向灯','右转向灯','危险报警闪光灯','前照灯');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (59,'1',5,3,'签订机动车交通事故责任强制保险合同后，被保险人应当把保险标志______。','放置在被保险车上','由保险公司保存','由工作单位保管','随身携带');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (60,'1',5,2,'机动车向右转弯、向右变更车道、超车完毕驶回原车道、靠路边停车时，______。   ','可以在转向的同时开启转向灯','可以先转向后开启转向灯','应当提前开启右转向灯','只要没有其他车辆可以不开转向灯');
INSERT INTO `tb_question` (`id`,`answers`,`score`,`level`,`title`,`option1`,`option2`,`option3`,`option4`) VALUES (61,'1',5,10,'年满60周岁，可以持有______准驾的驾驶证','C1、C2、C5','A1、C1、C5','A2、C2、C5','A1、A3、C5');
