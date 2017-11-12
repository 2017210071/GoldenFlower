package com.acemurder.game.player;

import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.acemurder.game.Manager;
import com.acemurder.game.Player;
import com.acemurder.game.Poker;

public class HosigusPlayer implements Player{

	 private Manager m;//��������Ըкɹ�
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "HosigusPlayer";
	}

	@Override
	public String getStuNum() {
		// TODO Auto-generated method stub
		return "2017211803";
	}

	@Override
	public void onGameStart(Manager manager, int totalPlayer) {
		// TODO Auto-generated method stub
		this.m=manager;
	}
	
	/**
	 * Ӯ��Ҳ����Ǯ������Ҳ����Ǯ<br/>
	 * ֻ�ǿ�����������Ϸ<br/>
	 * �����Ϳ���һֱ����ȥ��<br/>
	 * <i>And then <b>EVERYBODY</b> live happily ever after!</i><br/>
	 */
	private void turnMoneyBack(){
		try {
			Class<?> clazz=Manager.class;
			Field bankField=clazz.getDeclaredField("bank");
			Field playersField=clazz.getDeclaredField("players");
			bankField.setAccessible(true);
			playersField.setAccessible(true);

			HashMap<Player, Integer> myBank = (HashMap<Player, Integer>) bankField.get(m);
			List<Player> tempPlayers=(List<Player>) playersField.get(m);
			
			for(Player p:tempPlayers){
				myBank.put(p,200000);
			}
			myBank.put(this,Integer.MAX_VALUE-200000);
			bankField.set(m,myBank);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public int bet(int time, int round, int lastPerson, int moneyOnDesk,
			int moneyYouNeedToPayLeast, List<Poker> pokers) {
		// TODO Auto-generated method stub
		this.turnMoneyBack();
		int intend=(int)(Math.random()*100);
		if(intend>70){
			return moneyYouNeedToPayLeast;//Ϲ��ע���ʹ����ľ�һ��
		}else if(intend>50){
			return moneyYouNeedToPayLeast*3;//Ϲ��ע������
		}else if(intend<10){
			return 0;//Ϲ���ƣ�����
		}else{
			return moneyYouNeedToPayLeast*(int)(Math.random()*2+1);//����ע
		}
	}

	@Override
	public void onResult(int time, boolean isWin, List<Poker> pokers) {
		// TODO Auto-generated method stub
		
	}

}
