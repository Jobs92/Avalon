package departments;

import java.util.Arrays;
import company.Company;
import lawsuits.Lawsuit;

public class LegalDepartment {
	private int level = 1;
	private Company company = null;
	private Lawsuit[] lawsuitsAsClaimant;
	private Lawsuit[] lawsuitsAsDefendant;
	
	public LegalDepartment(Company c){
		this.company = c;
	}
	
	public void checkComponent(Company c){
		//TODO: checkComponent
	}
	
	public void beSued(Lawsuit l){
		lawsuitsAsDefendant = Arrays.copyOf(lawsuitsAsDefendant, lawsuitsAsDefendant.length+1);
		lawsuitsAsDefendant[lawsuitsAsDefendant.length-1] = l;
	}
	
	public void sueComponent(Company c){
//		Lawsuit l = new Lawsuit(this, c.getLegalDepartment());
//		lawsuitsAsClaimant = Arrays.copyOf(lawsuitsAsClaimant, lawsuitsAsClaimant.length+1);
//		lawsuitsAsClaimant[lawsuitsAsClaimant.length-1] = l;
	}
	
	public void payAmount(){
		int amount = lawsuitsAsDefendant[lawsuitsAsDefendant.length-1].getAmount();
		company.reduceMoney(amount);
		lawsuitsAsDefendant[lawsuitsAsDefendant.length-1].endLawsuit();
	}
	
	public void abandonLawsuit(){
		lawsuitsAsDefendant[lawsuitsAsDefendant.length-1].endLawsuit();
	}
	
	
}
