package com.me.zwali;

public class Constants {
	
	static enum achiev_types {Hello_world //100 kills 
								,Kid //500 kills
								,Rookie // 1000 kills
								,Killer // 2000 kills
								,Exterminator //5000 kills
								,Relentless //10killstreak
								,Pretty_damn_Good // 20killstreak
								,Unstopable //40 killstreak
								,GodLike //60 killstreak
								,Cop	// 10 buys
								,Entreperneur //20buys
								,Millionaire  //100 buys
								,Ignitor	// Machinegun Buy
								,Boss   //Shotgun Buy
								,Expert_Gunner //all upgrades
								,Im_Still_Alive //survive wave 10
								,Virgin_not //survive wave 30
								,Zombie_Anihalator // survive wave 60
								,Welcome_to_Zwali // 1hour
								,Enjoyable //2 hours
								,Aint_Zombies_Finite // 5 hours
								,Shit_I_missed_3_days_of_work //10 hours
								,Come_For_The_Zombies_Stay_For_The_Story // Story completed
								};
	

	
	int HEIGHT = 600;
	int WIDTH = 800;
	
	int XMARGIN = 150;
	int YMARGIN = 150;
	
	int XMIN = 50;
	int YMIN = 50;
	
	int FRAME_RATE = 60;
	
	int SPAWNRATE = 60;
	int FIRERATE = 15;


	int HEROI =	1;
	
	Cenario Home()
	{
		Cenario temp = new Cenario(5,Textures.qHome, new Vector(600,950), new Vector(950,750));
		temp.name = "Home";
		temp.Objects.add(new Vector(150,570));
		temp.Objects.add(new Vector(210,450));
		temp.Objects.add(new Vector(410,350));
		temp.Objects.add(new Vector(510,340));
		temp.Objects.add(new Vector(610,330));
		temp.Objects.add(new Vector(60,800));
		temp.Objects.add(new Vector(150,800));
		temp.Objects.add(new Vector(970,250));
		return temp;
	}
	
	Cenario Survival(Cenario bg)
	{
		Cenario temp = bg;
		temp.maxWaves = 0;
		temp.name = "Survival - " + bg.name;
		return temp;
	}
}
