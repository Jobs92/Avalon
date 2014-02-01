package campaigns;

import market.Market;
import utils.Message;
import company.Company;
import departments.Research;

public class ExplicitSpyingCampaign extends ExplicitResearchCampaign {
	private int spiedPlayer;
	private boolean isSued;

	public ExplicitSpyingCampaign(int spiedPlayer, SpyingCampaign campaign) {
		super(campaign);
		this.isSued = false;
		this.spiedPlayer = spiedPlayer;
	}

	public boolean isSued() {
		return isSued;
	}

	public void setSued(boolean isSued) {
		this.isSued = isSued;
	}

	public int getSpiedPlayer() {
		return spiedPlayer;
	}

	@Override
	public void campaignFinishedSuccessfully() {
		System.out.println("Class: " + this.getClass()
				+ ". SpyingCampaign finished successfully.");
		Company c = campaign.getDepartment().getCompany();
		Research r = (Research) campaign.getDepartment();
		r.addSpiedLevels(campaign.getLevel());

		// send message
		System.out.println("Class: " + this.getClass()
				+ ". Send success message to spying Player.");
		Message message = new Message();
		message.setTitle("Spionagekampagne erfolgreich durchgef�hrt.");
		message.setMessage("Spionagekampagne \""
				+ campaign.getTitle()
				+ "\" erfolgreich durchgef�hrt. Gewonnene Erkenntnisse werden verarbeitet.");
		message.setType(Message.GAME);
		message.setSourcePlayer(c.getId());
		message.setSourcePlayer(c.getId());
		c.addMessageToInbox(message);
	}

	@Override
	public void campaignFailed() {
		System.out.println("Class: " + this.getClass()
				+ ". SpyingCampaign failed.");
		Company c = campaign.getDepartment().getCompany();

		// send message
		System.out.println("Class: " + this.getClass()
				+ ". Send failed message to spying Player.");
		Message message = new Message();
		message.setTitle("Spionagekampagne nicht erfolgreich durchgef�hrt.");
		message.setMessage("Spionagekampagne ist aufgeflogen. Sie k�nnten von "
				+ Market.sharedInstance().getNameForId(getSpiedPlayer())
				+ " verklagt werden");
		message.setType(Message.GAME);
		message.setTargetPlayer(c.getId());
		c.addMessageToInbox(message);

		// send message to spied player
		System.out.println("Class: " + this.getClass()
				+ ". Send message to spied Player.");
		message = new Message();
		message.setTitle("Sie wurden ausspioniert!");
		message.setMessage("Sie wurden ausspioniert! Sie sollten "
				+ c.getName() + " verklagen!");
		message.setTargetPlayer(getSpiedPlayer());
		Market.sharedInstance().sendMessage(message);
	}

}
