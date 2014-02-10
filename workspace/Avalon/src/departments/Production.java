package departments;

import java.util.ArrayList;

import market.Market;
import otherclasses.ProductionJob;
import otherclasses.Resource;
import company.Company;
import config.Config;
import utils.Message;

public class Production extends Department implements Upgradable {
	private int level;
	private int capacity;
	private ArrayList<otherclasses.ProductionJob> allProductionJobs;

	public Production(Company company) {
		super(company);
		allProductionJobs = new ArrayList<otherclasses.ProductionJob>();
		level = 1;
		capacity = config.Config.getProductionCapacity();
		updateFixcost();
	}

	public void setCapacity(int x) {
		capacity = x;
	}

	public int getLevel() {
		return level;
	}

	public int getCapacity() {
		return capacity;
	}

	@Override
	public void simulate() {
		super.payFixcosts();
		for (ProductionJob job : allProductionJobs) {
			if (!job.isCompleted()) {

				int defectiveGoods = 0;
				for (int i = 0; i < job.getAmount(); i++) {
					if (company.changeMoney((-1)
							* Config.getProductionVariableCosts())) {
						Resource r = company.getWarehouse().getResource();
						if (utils.Probability.propability(r.getQuality())) {
							company.getWarehouse()
									.getSingleProduct(job.getLevel())
									.changeAmount(1);
						} else {
							defectiveGoods++;
						}
					} else {
						// Not enough money
						Message m = new Message();
						m.setTitle("Geld reicht nicht aus");
						m.setType(Message.GAME);
						m.setTargetPlayer(company.getId());
						m.setMessage("Sie haben nicht genügend Geld um alle Produktionsaufträge auszuführen!");
						Market.sharedInstance().sendMessage(m);

						break;
					}

				}
				job.setCompleted(true);
				if (defectiveGoods != 0) {

					// Inform User about defective goods
					Message m = new Message();
					m.setTitle("Ausschuss");
					m.setType(Message.GAME);
					m.setTargetPlayer(company.getId());
					m.setMessage("Bei der Ausführung Ihres Produktionsauftrags gab es einen Ausschuss von "
							+ defectiveGoods + " Smartphones.");
					Market.sharedInstance().sendMessage(m);
				}

			}
		}

	}

	public void produce(int level, int amount) {
		if (amount > 0) {

			// Test Capacity
			if (capacity < amount + countAlreadyNeededRessources()) {
				amount = capacity;

				// Fehlermeldung: keine kapazitaet
				Message m = new Message();
				m.setTitle("Kapazität reicht nicht aus");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage("Ihre Kapazitäten reichen nicht aus um " + amount
						+ " weitere Smartphones zu produzieren!");
				Market.sharedInstance().sendMessage(m);
			}

			// Test Ressources
			if (countAlreadyNeededRessources() + amount > company
					.getWarehouse().getAmountResources()) {

				// Fehlermeldung: keine ressourcen
				Message m = new Message();
				m.setTitle("Nicht genügend Ressourcen");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage("Sie haben nicht genügend Ressourcen um " + amount
						+ " weitere Smartphones zu produzieren!");
				Market.sharedInstance().sendMessage(m);

				amount = company.getWarehouse().getAmountResources()
						- countAlreadyNeededRessources();
			}

			ProductionJob job = new ProductionJob(level, amount);
			allProductionJobs.add(job);
		}
	}

	private int countAlreadyNeededRessources() {
		int amount = 0;
		for (ProductionJob job : allProductionJobs) {
			if (!job.isCompleted()) {
				amount += job.getAmount();
			}
		}
		return amount;
	}

	@Override
	public boolean upgrade() {
		if (this.level + 1 <= Config.getMaxLevelProduction()) {
			if (super.company.changeMoney((-1)
					* Config.getCostsUpgradeProduction())) {
				level++;
				updateFixcost();
				this.capacity = (int) (this.capacity * Config
						.getUpgradeProduction());
				return true;
			} else {
				Message m = new Message();
				m.setTitle("Geld reicht nicht aus");
				m.setType(Message.GAME);
				m.setTargetPlayer(company.getId());
				m.setMessage("Sie haben nicht genügend Geld um die Produktion zu upgraden!");
				Market.sharedInstance().sendMessage(m);
			}
		}
		return false;
	}

	public void downgrade() {
		if (level > 1) {
			level--;
			updateFixcost();
		}
	}

	public void updateFixcost() {
		fixcost = Config.getProductionFixcost() * level;
	}
}
