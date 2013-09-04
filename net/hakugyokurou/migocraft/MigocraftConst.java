package net.hakugyokurou.migocraft;

import java.util.Random;

/**
 * 常量类<br/><br/>
 * 
 * 包括一些字符串常量.
 * @author szszss
 */
public abstract class MigocraftConst {
	
	public static final String NETWORK_CHANNEL = "migocraft";
	
	public static final String BLOCK_CORE_NBT_COMPOUND = "migocraft";
	public static final String BLOCK_CORE_NBT_POWERED = "powered";
	
	public static final String CONFIG_BLOCK_ROTATOR = "rotator";
	public static final String CONFIG_BLOCK_ROTATOR_COMMENT = 
			"转轴砖块的编号\nThe block id of Rotator Block.";
	public static final String CONFIG_BLOCK_AGP = "agp";
	public static final String CONFIG_BLOCK_AGP_COMMENT = 
			"反重力板的编号\nThe block id of Antigravity panel.";
	public static final String CONFIG_BLOCK_RASA = "rasa";
	public static final String CONFIG_BLOCK_RASA_COMMENT = tearBedclothes();
	
	public static final String CONFIG_RENDER = "render";
	public static final String CONFIG_RENDER_RASA = "rasaRenderType";
	public static final String CONFIG_RENDER_RASA_COMMENT = bigBoobs();
	public static final String CONFIG_RENDER_AGP = "agpRenderType";
	public static final String CONFIG_RENDER_AGP_COMMENT = bigBoobs();
	
	//public static final String CONFIG_MISC = "misc";
	//public static final String CONFIG_MISC_MAX = "misc";
	
	private static String tearBedclothes()
	{
		Random random = new Random();
		String[] happyEllen = new String[]
				{
				//Delta Blue and Yakumo Gu
					"\"现在的问题就是,\"PureDark将手按在沙盘桌上,环视一遍四周在座的人,阴着脸压低了声音说,\"Zhuogu到底是什么人?\"\n\"这个,\"我回答说,\"根据我的判断,它应该是一只偶然获得了自我意识,并摆脱了八云紫的控制,从隙间里逃出来的触手怪.\"坐在我身旁的ICE听了后凑到我旁边说:\"没这么严重吧?\",\"但愿如此.\"我耸着肩回答.\n后来,我发现我们过去太天真了,Zhuogu不是八云紫的触手怪,她就是八云紫本尊.",
					"\"这里是蓝队,我们正在通过B通道.\"氙气战术灯驱散了地铁站内的黑暗,陶瓷地板反射出惨淡的白光,除了被血迹覆盖的地方.战术小队疾步穿过地下通道,拐过弯便已到了出口处,城市的灯火终于再一次映入他们的瞳孔,特工们小心翼翼地紧贴墙壁登上楼梯,枪口始终紧紧对着地铁站口.在即将踏上最后一格楼梯时,他几乎紧张到忘记了呼吸,幸运的是,地铁站外什么也没有.\n\"战术总台,这里是蓝队,我们已进入作战区.\"他按开了肩上的无线电,\"起码这次开头不错.\"他心里这么想着,顺便瞥了一眼手腕上的计时器,等等,为什么他们只用了二十秒就穿过了地铁站?他惊恐地望向四周.\n不再直流的时间背叛了他们.",
					"死寂会使人害怕,但喘气声也不会使人安心,左肩上的战术灯在刚才的战斗中早已经遗失了,他几乎是摸黑跑进了地铁站中,整个区域已经被锁定(Lockdown)了,这段完全没有照明的通道是唯一一条出口.他凭着记忆摸着墙一路小跑,竭力集中残存的理智从大脑中搜索出口的位置,让军法见鬼去吧,他想要的只有远远地逃离那个女人,冰冷的牢房远比黑暗中的恐惧来的要温暖,就连宪兵的枪口都要比那些邪恶的眼睛来的温情,死在绞刑架上总比像他的队友那样被不可名状的物质绞杀要好.\n他撞到了墙了,但立刻便左拐沿着墙继续跑下去,不能停下来!他几乎听见了那高跟鞋声就在他身后,正一阶阶地沿着台阶而下,他本应该甩开她的,他已经拐到了第四个弯--\n第四个弯,他猛然明白了什么,向同一个方向拐四次.他颓然跪下,放弃了徒劳的逃跑,高跟鞋声从他身后逼近,突然他猛地拔出手枪指向后方,做本能似的最后一次反抗,却发现身后只有空空的楼梯,身着紫衣的女人,正邪笑着从他的正前方走来.\n不再是三维的空间背叛了他.",
				};
		return happyEllen[random.nextInt(happyEllen.length)]+
				"\nDO NOT TOUCH IT IF YOU DON'T KNOW WHAT IT IS.";
	}
	
	/**
	 * Yeah,I'm V587er than the Microsoft! Come on and bit me,Linuxers!
	 * @return
	 */
	private static String bigBoobs()
	{
		Random random = new Random();
		String[] happyYukari = new String[]
				{
				//The story of Yakumos
					"很久以前,在幻想乡尚未建立的时候,在迷途之家只有两个人的身影的时候,隙间妖怪的式神 - 八云蓝,向她的主人,奈亚拉托提普 - 或者叫八云紫,提出了一个微不足道的请求 - 给她自己也收一只式神.",
					"八云紫面无表情地同意了这个请求,几天后,八云蓝领来了一个连路都走不稳的家伙,说\"请让这孩子成为我的式神吧!就叫她八云青吧.\",紫冷漠地摸了摸青的头,突然把手往下一按将青碾成了肉酱,随即转身离去并丢下一句话\"别用这种垃圾来败坏我们家的名声,赶快把尸体清理走拿去喂鸟.\",蓝将曾经是自己的式神的那滩肉泥铲走扔到了妖怪之山的山脚下,哭得像个泪人.其实就在她走后,紫从隙间里出来,赶走了乌鸦们然后将青的尸体埋了.",
					"几个月后,八云蓝又领来一个妖怪,说\"请让这孩子成为我的式神吧!就叫她八云绿吧.\",八云紫冷漠地摸了摸绿的头,突然手一捏将绿的头捏爆了,然后转身离去,丢给惨叫着搂住绿的无头尸的蓝一句话\"我不是说过别拿垃圾来败坏我们家吗,赶快把尸体扔到地灵殿去喂觉\",蓝抱着绿的尸体去了地灵殿,一路上哭得像个泪人.其实在她将尸体放到地灵殿并离开后不久,紫就从隙间里出来,为绿安了一个可分离的脑袋并将她复活了,顺便把旁边一只绿色头发的觉吓疯了.",
					"几年后,八云蓝领回来一个人类少女,说\"请让这孩子成为我的式神吧!就叫她八云黄吧.\",八云紫阴着脸摸了摸黄的头,顺便捏了捏按了按,黄始终安然无恙,就在蓝松了一口气的时候,紫说\"下次再拿垃圾来败坏家门的话我就连你一块杀掉.\",未等蓝明白发生了什么,紫便一把将黄推倒然后转身离去,蓝搂住黄发现她已经没有任何反应了,黄未能通过直面奈亚拉托提普的理智值检测,被吓成了植物人.\"把她关到库房里去,当做我今晚的晚饭.\"紫对着嚎啕大哭的蓝丢下这句话,然后摔门进入了自己的房间.其实紫没有吃掉黄,而是将她带到了一个神社.",
					"几十年后,八云蓝领回来一个妖怪,说\"请让这孩子成为我的式神吧,就叫她八云橙吧.\"八云紫阴着脸摸了摸橙的头,突然一拳打在橙的小腹上,将她击出几米远,蓝尖叫着扑过去,发现橙还有一口气,\"她通过了我的测试,我准许她成为你,八云蓝,的式神.\"紫踱步走上前来,\"但是,她的力量过弱,仍不足以拥有我们八云家的名号,因此现在她不能拥有八云姓.\",啜泣着跪在地上,擦拭头枕在自己膝盖上的橙嘴角边的血的蓝,点头答应了主人开出的条件.",
					"从此之后,迷途之家多了一个身影,也为这个\"家庭\"带来了欢声笑语,优雅而不苟言笑的女主人,潇洒又富有风度的女侍从,以及一位活泼好动的少女,成了这栋从未有人类敢靠近的建筑中,一道人人皆知的风景线."
				};
		return happyYukari[random.nextInt(happyYukari.length)]+
				"\nThe type number of Rasa's render.You'd better don't touch it if you don't know what you are doing.";
	}

}
