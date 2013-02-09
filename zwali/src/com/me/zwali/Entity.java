package com.me.zwali;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class Entity {
	
	Vector pos;
	Vector prevpos;
	Vector size;
	Vector vel;
	
	float radii;
	boolean circle;
	
	float speed;
	double angle;
	
	int Health;
	int power;
	
	Collision collisions;
	
	
	Sprite image;
	
	Entity( Vector pos, Vector size, boolean circle, Sprite T)
	{
		this.pos = pos;
		this.size = size;
		this.radii = (float)((size.x+size.y)/4.0);
		this.angle = 0;
		this.power = 0;
		collisions = new Collision();
		image = T;
		image.setSize((float)size.x, (float)size.y);
		this.circle = circle;
	}
	
	public boolean Update()
	{
		return true;
	}
	
	public void UpdatePos( Background BACK)
	{

		Vector deltaPos = new Vector( vel.x * speed, vel.y * speed);

		for(StaticObj obj: BACK.Objects)
		{
			Vector4 A = collisions.Coll(this, obj);
			{
				Vector proj = deltaPos.proj(A.A);
				Vector perp = deltaPos.proj(new Vector ( -A.A.y, A.A.x));
				if( proj.SizeSQ() > A.B*A.B)
				{
					Vector M = new Vector(A.A.x*A.B, A.A.y*A.B);
					if( M.dot(proj) <0)
					{
						deltaPos = new Vector( perp.x, perp.y);
					}
					else
					{
						deltaPos = new Vector(A.A.x*A.B + perp.x, A.A.y*A.B + perp.y);
					}
				}
				
			}
		}
		
		for( UnStaticObj obj: BACK.UnObjects)
		{
			
			Vector4 A = collisions.Coll(this, obj);		
			if(obj.alive)
			{
				{
					Vector proj = deltaPos.proj(A.A);
					Vector perp = deltaPos.proj(new Vector ( -A.A.y, A.A.x));
					if( proj.SizeSQ() > A.B*A.B)
					{
							Vector M = new Vector(A.A.x*A.B, A.A.y*A.B);
							if( M.dot(proj) <0)
							{
								deltaPos = new Vector( perp.x, perp.y);
							}
							else
							{
								deltaPos = new Vector(A.A.x*A.B + perp.x, A.A.y*A.B + perp.y);
							}
							
					}
				}
			}
		}
		
			
		pos.x += deltaPos.x;
		pos.y += deltaPos.y;
		
	}
	
	
	
	public boolean Collide( Entity other)
	{
		if( (pos.x - other.pos.x)*(pos.x - other.pos.x) + (pos.y - other.pos.y)*(pos.y - other.pos.y) <= (this.radii + other.radii)* (this.radii + other.radii) )
			return true;
		
		return false;	
	}
	
	public void draw( Vector Disp, SpriteBatch batch)
	{
		image.setRotation((float) angle);
		image.setOrigin((float)( size.x/2), (float)(size.y/2));
		image.setPosition((float)pos.x -(float)size.x/2 - (float)Disp.x, (float)pos.y - (float)size.y/2 - (float)Disp.y);	
		image.draw(batch);
		
	}
	


}
