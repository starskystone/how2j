package com.how2j.idao;

import com.how2j.bean.Hero;

public interface IHeroDao {
	public void add(Hero hero);
	public void update(Hero hero);
	public void delete(Hero hero);
	public Hero select();
	public int getTotal();
}
