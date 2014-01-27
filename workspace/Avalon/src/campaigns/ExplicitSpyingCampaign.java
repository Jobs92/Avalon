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
	protected void campaignFinishedSuccessfully() {
		Company c = campaign.getDepartment().getCompany();
		Research r = (Research) campaign.getDepartment();
		r.addSpiedLevels(campaign.getLevel());

		// send message
		Message message = new Message();
		message.setTitle("Spionagekampagne erfolgreich durchgeführt.");
		message.setMessage("Spionagekampagne \""
				+ campaign.getTitle()
				+ "\" erfolgreich durchgeführt. Gewonnene Erkenntnisse werden verarbeitet.");
		message.setType(Message.GAME);
		message.setSourcePlayer(c.getId());
		message.setSourcePlayer(c.getId());
		c.addMessageToInbox(message);
	}

	@Override
	protected void campaignFailed() {
		Company c = campaign.getDepartment().getCompany();

		// send message
		Message message = new Message();
		message.setTitle("Spionagekampagne nicht erfolgreich durchgeführt.");
		message.setMessage("Spionagekampagne ist aufgeflogen. Sie könnten von"
				+ Market.sharedInstance().getNameForId(getSpiedPlayer())
				+ " verklagt werden");
		message.setType(Message.GAME);
		message.setTargetPlayer(c.getId());
		c.addMessageToInbox(message);

		// send message to spied player
		message.setTitle("Sie wurden ausspioniert!");
		message.setMessage("Sie wurden ausspioniert! Sie sollten "
				+ c.getName() + " verklagen!");
		Market.sharedInstance().sendMessage(message);
	}

}
