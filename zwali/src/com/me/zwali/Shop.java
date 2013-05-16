package com.me.zwali;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.ruthlessgames.api.StylesManager;
import com.ruthlessgames.api.UI;




public class Shop extends UI{

	public BitmapFont shopfont;
	Conceito MainGame;
	

	Vector mpos = new Vector(0,0);
	Vector exit = new Vector(0,0);
	Vector btnExitPos = new Vector(35, 50);
	
	List <Item> itens = new ArrayList<Item>(10);
	
	
	boolean highlight[] = new boolean[3];
	static boolean wizardmode = false;
	static Random rdm = new Random();
	
	Table AmmoTable;
	
	Label LText = new Label("", StylesManager.arial15);
	Label Lpstats = new Label("", StylesManager.arial15);
	String aux;
	int index;
	int ammoindex;
	
	public Shop(Conceito Main)
	{
		super(Conceito.batch, Main.font,false);
	
		MainGame = Main;
		shopfont = Main.font;
		
		
		
		TextureRegion bg = new TextureRegion(Textures.shopIM,0,0,(int)Textures.shopIM.getWidth(),(int)Textures.shopIM.getHeight());
        table.setBackground(new TextureRegionDrawable(bg));
        
        
        Lpstats.setText("Money = " + ScreenChooser.Player1.getMoney() + "$\nXP = " + ScreenChooser.Player1.getXP() + "\nHealth = " + ScreenChooser.Player1.Health + "/" + ScreenChooser.Player1.MaxHp + "\nArmor = " + ScreenChooser.Player1.armor + "/" + ScreenChooser.Player1.MaxArmor);
		Lpstats.setPosition(650, 550);
		
		LText.setPosition(450, 105);
        
		//exit
		final TextButton buttonExit = new TextButton("Exit", StylesManager.btnGray);
		buttonExit.setBounds((float)btnExitPos.x, (float)btnExitPos.y, 110, 65);
		buttonExit.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	               	
	                return true;
	        }
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonExit.getWidth() && x >0 && y<buttonExit.getHeight() && y > 0)
	        	MainGame.setScreen(MainGame.questsScreen);
	        	}
	        });
		
		
		
		
		
		final TextButton buttonEquip = new TextButton("Equip", StylesManager.btnBlue);
		buttonEquip.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	                return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonEquip.getWidth() && x >0 && y<buttonEquip.getHeight() && y > 0)
	        		if(ScreenChooser.Player1.hasGun[0] && index == 0) 
	        			ScreenChooser.Player1.setCurGun(0);
	        		else if(ScreenChooser.Player1.hasGun[2] && index == 1)
	        			ScreenChooser.Player1.setCurGun(2);
	        		else if(ScreenChooser.Player1.hasGun[1] && index == 2)
	        			ScreenChooser.Player1.setCurGun(1);
	        	}
	        });
		buttonEquip.setBounds((float)btnExitPos.x+260, (float)btnExitPos.y, 110, 65);
		
		//Minigun
		Image imgMinigun = new Image(Textures.minigun);
		imgMinigun.setBounds(210, 350 , 140, 140);
		
		final TextButton buttonMinigun = new TextButton("", Textures.imgMinigun);
		buttonMinigun.setBounds(210, 350 , 140, 140);
		buttonMinigun.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	          
				 return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonMinigun.getWidth() && x >0 && y<buttonMinigun.getHeight() && y > 0)
	        		index = 2;
	        	Labelhandler();
	        	}
	        });
		
		//Shotgun
		Image imgShotgun = new Image(Textures.shotgun);
		imgShotgun.setBounds(135, 350 , 70, 120);
		
		final TextButton buttonShotgun = new TextButton("", Textures.imgShotgun);
		buttonShotgun.setBounds(135, 350 ,70, 120);
		buttonShotgun.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				 return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonShotgun.getWidth() && x >0 && y<buttonShotgun.getHeight() && y > 0)
	        		index = 1;
			 	Labelhandler();
	        	}
	        });
		
		Image imgPistol = new Image(Textures.pistol);
		imgPistol.setBounds(70, 375 , 50, 90);
		
		final TextButton buttonPistol = new TextButton("", Textures.imgPistol);
		buttonPistol.setBounds(70, 375 ,50, 90);
		buttonPistol.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {				   
				 return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonPistol.getWidth() && x >0 && y<buttonPistol.getHeight() && y > 0)
	        		index = 0;
               		Labelhandler();
	        	}
	        });
		
		final TextButton buttonHealth = new TextButton("", Textures.btnHealth);
		buttonHealth.setBounds(375, 295 ,50, 50);
		buttonHealth.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {  
				 return true;	 
	        }

	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonHealth.getWidth() && x >0 && y<buttonHealth.getHeight() && y > 0)
	        		index = 3;
	        		Labelhandler(); 
	        	}
	        });
		
		final TextButton buttonArmor = new TextButton("", Textures.btnArmor);
		buttonArmor.setBounds(575, 295 ,50, 50);
		buttonArmor.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
	                return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonArmor.getWidth() && x >0 && y<buttonArmor.getHeight() && y > 0)
	        		index = 4;
	        		Labelhandler();
	        	}
	        });
		
		final TextButton buttonAmmo = new TextButton("", Textures.btnAmmo);
		buttonAmmo.setBounds(575, 245 ,50, 50);
		buttonAmmo.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				 return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonAmmo.getWidth() && x >0 && y<buttonAmmo.getHeight() && y > 0)
	        		index = 5;
	        		Labelhandler();
	        		
	        	}
	        });
		
		final TextButton Pammo = new TextButton("Pistol", StylesManager.skin);
		Pammo.setSize(70,30);
		Pammo.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				 return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < Pammo.getWidth() && x >0 && y<Pammo.getHeight() && y > 0)
	        	{
	        		ammoindex = 0;
	        		Labelhandler();
	        	}
	        	}
	        });
		
		final TextButton Sammo = new TextButton("Shotgun", StylesManager.skin);
		Sammo.setSize(70,30);
		Sammo.setX(80);
		Sammo.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				 return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < Sammo.getWidth() && x >0 && y<Sammo.getHeight() && y > 0)
	        	{
	        		ammoindex = 1;
	        		Labelhandler();
	        	}
	        	}
	        });
		
		final TextButton Mammo = new TextButton("Minigun", StylesManager.skin);
		
		Mammo.setSize(70,30);
		Mammo.setX(160);
		Mammo.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				 return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < Mammo.getWidth() && x >0 && y<Mammo.getHeight() && y > 0)
	        	{
	        		ammoindex = 2;
	        		Labelhandler();
	        	}
	        	}
	        });
		
		AmmoTable = new Table();
		AmmoTable.setPosition(490, 45);
		AmmoTable.addActor(Pammo);
		AmmoTable.addActor(Sammo);
		AmmoTable.addActor(Mammo);
		AmmoTable.setVisible(false);
		
		final TextButton buttonRes = new TextButton("", Textures.btnRes);
		buttonRes.setBounds(375, 245 ,50, 50);
		buttonRes.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				 return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonRes.getWidth() && x >0 && y<buttonRes.getHeight() && y > 0)
	        		index = 6;
	        	Labelhandler();
	        	}
	        });
		
		final TextButton buttonACC = new TextButton("", Textures.btnACC);
		buttonACC.setBounds(475, 245 ,50, 50);
		buttonACC.addListener(new InputListener() {
			 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {  
				 return true;
	        }
			 
	        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
	        	if(x < buttonACC.getWidth() && x >0 && y<buttonACC.getHeight() && y > 0)
	        		index = 7;
	        		Labelhandler();
	        	}
	        });
		
		
		//Buy
				final TextButton buttonBuy = new TextButton("Buy", StylesManager.btnGreen);
				buttonBuy.setBounds((float)btnExitPos.x+130, (float)btnExitPos.y, 110, 65);
				buttonBuy.addListener(new InputListener() {
					 public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
						 
						 return true;
			        }
					 
			        public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
			        	
			        	int moneyprev = ScreenChooser.Player1.money;
			        	if(x < buttonBuy.getWidth() && x >0 && y<buttonBuy.getHeight() && y > 0)
				        	{
			        		switch (index)
			        		{
			        		case 0:
					        	{
					        		if(ScreenChooser.Player1.UpgPwrPistol < 3 && ScreenChooser.Player1.getXP() >= ((ScreenChooser.Player1.UpgPwrPistol+1)*100+ (ScreenChooser.Player1.UpgPwrPistol*100)))
					        		{
					        			
					        			ScreenChooser.Player1.setXP(ScreenChooser.Player1
												.getXP() - ((ScreenChooser.Player1.UpgPwrPistol+1*100) + (ScreenChooser.Player1.UpgPwrPistol*100))) ;
										ScreenChooser.Player1.UpgPwrPistol += 1;	
										ScreenChooser.Player1.InvListWeapons.get(0).power += 5;
										ScreenChooser.Player1.InvListWeapons.get(0).power2 += 5;
					        		}
					        		buttonPistol.isPressed();
					        		break;
					        	}
			        		case 1:
			        		{
						        	if(ScreenChooser.Player1.hasGun[2])
						        	{
						        		if(ScreenChooser.Player1.getXP() >= ((ScreenChooser.Player1.UpgPwrShotgun+1)*250 + ScreenChooser.Player1.UpgPwrShotgun*100))
										{
											if(ScreenChooser.Player1.UpgPwrShotgun < 3 && ScreenChooser.Player1.getXP() >= ((ScreenChooser.Player1.UpgPwrPistol+1)*100*2))
											{
												ScreenChooser.Player1
														.setXP(ScreenChooser.Player1
																.getXP()
																- ((ScreenChooser.Player1.UpgPwrShotgun+1)*250 + ScreenChooser.Player1.UpgPwrShotgun*100)) ;
												ScreenChooser.Player1.UpgPwrShotgun += 1;	
												ScreenChooser.Player1.InvListWeapons.get(2).power += 3;
												ScreenChooser.Player1.InvListWeapons.get(2).power2 += 3;
											
											}
										}
						        	}
						        	else
						        	{
						        		if(ScreenChooser.Player1.getMoney() >= itens.get(7).price ) 
										{
												ScreenChooser.Player1.addMoney(-1*itens.get(7).price);
												ScreenChooser.Player1.CurGun = 2;
												ScreenChooser.Player1.hasGun[2] = true;
												Conceito.achiev_checker.update(Constants.achiev_types.Boss, 1);
										}
						        	}
						        	break;
						        }
			        		case 2:
			        		{
					        		if(ScreenChooser.Player1.hasGun[1])
						        	{
					        			if( ScreenChooser.Player1.getXP() >= ((ScreenChooser.Player1.UpgPwrMinigun+1)*500 + ScreenChooser.Player1.UpgPwrMinigun*200))
										{
											if(ScreenChooser.Player1.UpgPwrMinigun < 3)
											{
												ScreenChooser.Player1
														.setXP(ScreenChooser.Player1
																.getXP()
																- ((ScreenChooser.Player1.UpgPwrMinigun+1)*500 + ScreenChooser.Player1.UpgPwrMinigun*200)) ;
												ScreenChooser.Player1.UpgPwrMinigun += 1;	
												ScreenChooser.Player1.InvListWeapons.get(2).power += 1;
												ScreenChooser.Player1.InvListWeapons.get(2).power2 += 1;
											}
										}
						        	}
					        		else
					        		{
					        			if(ScreenChooser.Player1.getMoney() >= itens.get(8).price) //add botoes
										{
										
											ScreenChooser.Player1.addMoney(-1*itens.get(8).price);
											ScreenChooser.Player1.hasGun[1] = true;
											Conceito.achiev_checker.update(Constants.achiev_types.Ignitor, 1);
										}
					        		}
					        		break;
					        	}
			        		case 3:
					        	{			        		
									if(ScreenChooser.Player1.getMoney() > itens.get(0).price && ScreenChooser.Player1.Health < ScreenChooser.Player1.MaxHp)
									{
										
										ScreenChooser.Player1.addMoney(-1*itens.get(0).price);
										ScreenChooser.Player1.Health += 20;
										if(ScreenChooser.Player1.Health > ScreenChooser.Player1.MaxHp)
											ScreenChooser.Player1.Health = ScreenChooser.Player1.MaxHp;
									}
									break;
					        	}
			        		case 4:
					        	{
									if(ScreenChooser.Player1.getMoney() > itens.get(3).price && ScreenChooser.Player1.armor < ScreenChooser.Player1.MaxArmor)
									{
										
										ScreenChooser.Player1.addMoney(-1*itens.get(3).price);
										ScreenChooser.Player1.armor += 10;
										if(ScreenChooser.Player1.armor > ScreenChooser.Player1.MaxArmor)
											ScreenChooser.Player1.armor = ScreenChooser.Player1.MaxArmor;
									}	
									break;
					        	}
			        		case 5://ammo
					        	{
					        		
									switch(ammoindex){
									case 0:
									{
										if(ScreenChooser.Player1.getMoney() > 50 && ScreenChooser.Player1.InvListWeapons.get(0).ammo < ScreenChooser.Player1.InvListWeapons.get(0).Maxammo )
										{
										
											ScreenChooser.Player1.addMoney(-50);
											ScreenChooser.Player1.InvListWeapons.get(0).ammo += ScreenChooser.Player1.InvListWeapons.get(0).MAXCAR;
											if(ScreenChooser.Player1.InvListWeapons.get(0).ammo > ScreenChooser.Player1.InvListWeapons.get(0).Maxammo)
												ScreenChooser.Player1.InvListWeapons.get(0).ammo = ScreenChooser.Player1.InvListWeapons.get(0).Maxammo;
										}
										break;
									}
									case 1:
									{
											if(ScreenChooser.Player1.getMoney() > 120 && ScreenChooser.Player1.InvListWeapons.get(2).ammo < ScreenChooser.Player1.InvListWeapons.get(2).Maxammo )
											{
												
												ScreenChooser.Player1.addMoney(-120);
												ScreenChooser.Player1.InvListWeapons.get(2).ammo += ScreenChooser.Player1.InvListWeapons.get(2).MAXCAR;
												if(ScreenChooser.Player1.InvListWeapons.get(2).ammo > ScreenChooser.Player1.InvListWeapons.get(2).Maxammo)
													ScreenChooser.Player1.InvListWeapons.get(2).ammo = ScreenChooser.Player1.InvListWeapons.get(2).Maxammo;
											}
											break;
									}
									case 2:
									{
										if(ScreenChooser.Player1.getMoney() > 300 && ScreenChooser.Player1.InvListWeapons.get(1).ammo < ScreenChooser.Player1.InvListWeapons.get(1).Maxammo )
										{
										
											ScreenChooser.Player1.addMoney(-300);
											ScreenChooser.Player1.InvListWeapons.get(1).ammo += ScreenChooser.Player1.InvListWeapons.get(1).MAXCAR;
											if(ScreenChooser.Player1.InvListWeapons.get(1).ammo > ScreenChooser.Player1.InvListWeapons.get(1).Maxammo)
												ScreenChooser.Player1.InvListWeapons.get(1).ammo = ScreenChooser.Player1.InvListWeapons.get(1).Maxammo;
										}
										break;
									}
									
									}
									
									break;
								}
			        		case 6:
					        	{
					        		
									if(ScreenChooser.Player1.getMoney() > itens.get(2).price && ScreenChooser.Player1.buildQuant < 10)
									{
										ScreenChooser.Player1.addMoney(-1*itens.get(2).price);
										ScreenChooser.Player1.buildQuant += 1;
									}
									break;
									
					        	}
			        		case 7:
					        	{
									if(ScreenChooser.Player1.getXP() > (ScreenChooser.Player1.UpgACC+1)*100 + (ScreenChooser.Player1.UpgACC*250) && ScreenChooser.Player1.UpgACC < 3)
									{
										ScreenChooser.Player1.setXP(ScreenChooser.Player1
												.getXP() - ((ScreenChooser.Player1.UpgACC+1)*100 + (ScreenChooser.Player1.UpgACC*250)));
										ScreenChooser.Player1.ACCdefault -= 2;
										ScreenChooser.Player1.UpgACC += 1;
									}	
									break;
					        	}
					        
			        		}
			        		if(moneyprev > ScreenChooser.Player1.money) Conceito.stats.buys++;
				        	Labelhandler();
				        	}
			        	
			        	}
			        });
		
		table.debug();
		

		stage.addActor(AmmoTable);
		
		LabelStyle labelstyle = new LabelStyle();
		labelstyle.font = this.shopfont;
		
		table.addActor(buttonEquip);
		table.addActor(buttonBuy);
		table.addActor(buttonExit);	
		table.addActor(imgMinigun);
		table.addActor(buttonMinigun);
		table.addActor(imgShotgun);
		table.addActor(buttonShotgun);
		table.addActor(imgPistol);
		table.addActor(buttonPistol);
		table.addActor(buttonHealth);
		table.addActor(buttonArmor);
		table.addActor(buttonAmmo);
		table.addActor(buttonRes);
		table.addActor(buttonACC);
		table.addActor(buttonHealth);
		table.addActor(LText);
		table.addActor(Lpstats);
		populateItens();
	}
	
	public void populateItens()
	{
		for(int i = 0; i < 9; i++)
			itens.add(new Item(i, ScreenChooser.Player1));
	}
	
	public void Labelhandler(){
		Lpstats.setText("Money = " + ScreenChooser.Player1.getMoney() + "$\nXP = " + ScreenChooser.Player1.getXP() + "\nHealth = " + ScreenChooser.Player1.Health + "/" + ScreenChooser.Player1.MaxHp + "\nArmor = " + ScreenChooser.Player1.armor + "/" + ScreenChooser.Player1.MaxArmor);
		AmmoTable.setVisible(false);
		switch (index){
			case 0:
			{
				aux = "Pistol\nPower: "+ ScreenChooser.Player1.UpgPwrPistol +"/" + "3\nAmmo: " + (ScreenChooser.Player1.InvListWeapons.get(0).ammoTotal + ScreenChooser.Player1.InvListWeapons.get(0).ammo) + "/" + ScreenChooser.Player1.InvListWeapons.get(0).Maxammo;
				if(ScreenChooser.Player1.UpgPwrPistol < 3)
					aux = aux + "\nUpgrade price: " + ((ScreenChooser.Player1.UpgPwrPistol+1*100) + (ScreenChooser.Player1.UpgPwrPistol*100)) + "XP";
				else
					aux = aux + "\nMaxed out";
				break;
			}
			case 1:
			{
				aux = "Shotgun";
				if(ScreenChooser.Player1.hasGun[2])
				{
					aux += "\nPower: " + ScreenChooser.Player1.UpgPwrShotgun +"/" + "3\nAmmo: " + (ScreenChooser.Player1.InvListWeapons.get(2).ammoTotal + ScreenChooser.Player1.InvListWeapons.get(2).ammo) + "/" + ScreenChooser.Player1.InvListWeapons.get(2).Maxammo;
					if(ScreenChooser.Player1.UpgPwrShotgun < 3)
						aux += "\nUpgrade price: " + ((ScreenChooser.Player1.UpgPwrShotgun+1*250) + (ScreenChooser.Player1.UpgPwrShotgun*100)) + "XP";
					else
						aux += "\nMaxed out";
	
	
				}
				else
					aux += "Price: " + itens.get(7).price + "$";
				break;
			}
			case 2:
			{
				aux = "Minigun";
	
				if(ScreenChooser.Player1.hasGun[1])
				{
					aux += "\nPower: " + ScreenChooser.Player1.UpgPwrMinigun +"/" + "3\nAmmo: " + (ScreenChooser.Player1.InvListWeapons.get(1).ammoTotal + ScreenChooser.Player1.InvListWeapons.get(1).ammo) + "/" + ScreenChooser.Player1.InvListWeapons.get(1).Maxammo;
	
					if(ScreenChooser.Player1.UpgPwrMinigun < 3)
						aux += "\nUpgrade price: " + ((ScreenChooser.Player1.UpgPwrMinigun+1*250) + (ScreenChooser.Player1.UpgPwrMinigun*100)) + "XP";
					else
						aux += "\nMaxed out";						
				}
				else
				{
					aux += "\nPrice: " + itens.get(8).price + "$";
				}
				break;
	
			}
			case 3:
			{
				aux = ("Medic Kit\nPrice: " + itens.get(0).price +"$\nDescription: +20 hp to you current.");
				break;
			}
			case 4:
			{
				aux = "Armor\nPrice: " + itens.get(3).price + "$\nDescription: +10 Armor to you current";
				break;
			}
			case 5:
			{
				
				AmmoTable.setVisible(true);
				switch (ammoindex){
				case 0:
					aux = "Ammo\nPrice: " + 50 + "$\nDescription: Pistol Ammo selected";
					break;
				case 1:
					aux = "Ammo\nPrice: " + 120 + "$\nDescription: Shotgun Ammo selected";
					break;
				case 2:
					aux = "Ammo\nPrice: " + 300 + "$\nDescription: Minigun Ammo selected";
					break;
				}
				break;
			}
			case 6:
			{
				aux = "Resources\nPrice: " + itens.get(2).price + "$\nDescription: +1 Resource for building purposes"; 
				break;
			}
			case 7:
			{
				aux = "Accuracy " + ScreenChooser.Player1.UpgACC + "/3";
				if(ScreenChooser.Player1.UpgACC < 3){
					aux += "\nPrice: " + ((ScreenChooser.Player1.UpgACC+1)*100 + (ScreenChooser.Player1.UpgACC*250)) + "XP\nDescription: +1 to your current accuracy";
				}
				else
					aux += "Maxed out";
				break;
			} 
		}
		LText.setText(aux);

	}
	
	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		Conceito.batch.begin();
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
        
   
        
        Conceito.batch.end();
        Conceito.batch.begin();
		Conceito.batch.end();
	}

	@Override
	public void resize(int width, int height) {
		stage.setViewport(width, height, true);
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		Gdx.input.setInputProcessor(stage);
		table.getColor().a = 0;
		table.addAction(Actions.fadeIn(0.5f));
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		shopfont.dispose();
		stage.dispose();
	}
	
	
		
	
	

	
	public void animateWaveIncoming(int WaveNr, int timer, SpriteBatch batch)
	{
		Quest.font.draw(batch,"Wave incomming! Number: " + Integer.toString(WaveNr),425, 508);
	}	

}
