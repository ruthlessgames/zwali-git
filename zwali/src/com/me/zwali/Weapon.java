package com.me.zwali;

import com.badlogic.gdx.Gdx;

public class Weapon
{
	public int power,power2;
	public int FIRERATE;
	public int MAXCAR;
	public int ammo;
	public int ammoTotal;
	public int Type;
	public int powerbuff;
	private int speed;
	private int fireTimer;
	private int reloadTimer;
	private int RELOADTIME;
	public boolean buff;
	boolean reloading;
	boolean lethalarea;
	Vector size;
	Vector pos;
	boolean can_anim = false;
	int timer = 0;
	
	public Weapon( int type, int difficulty)
	{
		this.Type = type;
		reloading = false;
		buff = false;
		switch (type)
		{
		case 0:
			powerbuff = 35;
			power = 25;
			power2 = 25;
			if(difficulty == 3)
			{
				power += 6;
				power2 += 6;
			}
			speed = 30;
			FIRERATE = 30;
			MAXCAR = 15;
			RELOADTIME = 120;
			lethalarea = false;
			ammoTotal = 150;
			this.size = new Vector(30,10);
			break;
		case 1:
			powerbuff = 13;
			power = 10;
			power2 = 10;
			if(difficulty == 3)
			{
				power += 2;
				power2 += 2;
			}
			speed = 20;
			FIRERATE = 4;
			MAXCAR = 300;
			RELOADTIME = 300;
			lethalarea = false;
			ammoTotal = 1500;
			this.size = new Vector(60,30);
			break;
		case 2:
			powerbuff = 35;
			power = 25;
			power2 = 25;
			if(difficulty == 3)
			{
				power += 4;
				power2 += 4;
			}
			speed = 25;
			FIRERATE = 30;
			MAXCAR = 12;
			RELOADTIME = 180;
			lethalarea = true;
			ammoTotal = 90;
			this.size = new Vector(60,15);
			break;
		}

		ammo = MAXCAR;
		
		
	}
	
	public boolean Shoot()
	{
		if (!buff) 
		{
			if (ammo > 0 && fireTimer >= FIRERATE && !reloading) {
				fireTimer = 0;
				ammo--;
				can_anim = true;
				if (ammo == 0)
					this.Reload();
				return true;
			}

			else if (ammo == 0 && fireTimer >= FIRERATE && ammoTotal > 0) {
				this.Reload();
				return false;
			}
			
		}
		else 
		{
			can_anim = true;
			return true;
		}
		return false;
	}
	
	public void Reload()
	{
				
		if( !reloading  && ammoTotal > 0)
		{
			int ammoneed = MAXCAR - ammo;
			reloading = true;
			reloadTimer = 0;
			ammo += minv( ammoneed, ammoTotal);
			ammoTotal -=ammoneed;
			if(ammoTotal < 0)
			{
				ammoTotal = 0;
			}
		}
	}
	
	public void Update(Player Player1, Vector Disp)
	{
		
		if( ammo==0 && fireTimer >= FIRERATE && ammoTotal >0)
		{
			this.Reload();
		}
		
	
		if( reloading)
		{
			InvMenu.reloading = true;
			reloadTimer++;
			if( reloadTimer >= RELOADTIME)
			{
				InvMenu.reloading = false;
				reloading = false;
			}
		}
		else{
			
			fireTimer++;
		}
		
		if(can_anim)
		{
			timer++;
			if(timer >= 10){
				can_anim = false;
				timer = 0;
			}
			
			int MouseX = Gdx.input.getX();
			int MouseY = 600 - Gdx.input.getY();
			Vector p = Player1.getPos();
			Vector d = Disp; 
			Vector c = new Vector(MouseX - p.x - 45 + d.x , MouseY- p.y -45 + d.y);
			c.normalize();
			c.rotate(-6);
			Vector dir = new Vector(p.x + Player1.size.x/2 - 9  + c.x*55, p.y + Player1.size.y/2 - 9 + c.y*55 );
			
			Textures.bul_art.setOrigin(Textures.bul_art.getWidth()/2,Textures.bul_art.getHeight()/2);
			Textures.bul_art.setSize(20,20);
			Textures.bul_art.setRotation((float)Player1.angle);
			Textures.bul_art.setPosition((float)(dir.x-d.x),(float)(dir.y-d.y));
			Textures.bul_art.draw(Conceito.batch);
	
		}
	}

	
	public int getSpeed()
	{
		return this.speed;
	}
	
	public int minv(int a, int b)
	{
		return (a<b)?a:b;
	}

}


