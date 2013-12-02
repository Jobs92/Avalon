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
		Research r = (Research) campaign.getDepartment();
		r.addSpiedLevels(campaign.getLevel());

		Company c = campaign.getDepartment().getCompany();
		Message message = new Message();
		message.setTitle("Spionagekampagne erfolgreich durchgeführt!");
		message.setMessage("Spionagekampagne \""
				+ campaign.getTitle()
				+ "\" erfolgreich durchgeführt.  Gewonnene Erkenntnisse werden verarbeitet.");
		message.setType(Message.GAME);
		message.setTargetPlayer(c.getId());
		c.addMessageToInbox(message);
	}

	@Override
	protected void campaignFailed() {
		Company c = campaign.getDepartment().getCompany();
		Message message = new Message();
		message.setTitle("Spionagekampagne fehlgeschlagen!");
		message.setMessage("Spionagekampagne \"" + campaign.getTitle()
				+ "\" ist aufgeflogen. Sie könnten verklagt werden!");
		message.setType(Message.GAME);
		message.setTargetPlayer(c.getId());
		c.addMessageToInbox(message);
		// send message to opponent
		message.setTitle("Sie wurden ausspioniert!");
		message.setMessage("Sie wurden ausspioniert. Sie sollten den Gegner verklagen.");
		message.setSourcePlayer(c.getId());
		message.setTargetPlayer(this.spiedPlayer);
		Market.sharedInstance().sendMessage(message);
	}

}
