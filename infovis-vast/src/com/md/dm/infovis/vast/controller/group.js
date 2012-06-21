function(doc, prev) { 
	prev.count += 1; 
	
	if (doc.machineClass == 'atm') { 
		prev.atm +=1;
	} else if (doc.machineClass == 'server') { 
		prev.server +=1;
	} else if (doc.machineClass == 'workstation') { 
		prev.workstation +=1;
	}  
	
	
	if (doc.machineFunction == 'compute') { 
		prev.compute +=1;
	} else if (doc.machineFunction == 'email') { 
		prev.email +=1;
	} else if (doc.machineFunction == 'file server') { 
		prev.fileserver +=1;
	}  else if (doc.machineFunction == 'loan') { 
		prev.loan +=1;
	}  else if (doc.machineFunction == 'multiple') { 
		prev.multiple +=1;
	}  else if (doc.machineFunction == 'office') { 
		prev.office +=1;
	}  else if (doc.machineFunction == 'teller') { 
		prev.teller +=1;
	}  else if (doc.machineFunction == 'web') { 
		prev.web +=1;
	}  else if (doc.machineFunction == '') { 
		prev.empty +=1;
	}  
	
	if(doc.statusList.length == 0) 
		return; 
	
	switch(doc.statusList[0].policyStatus) { 
		case 1: 
			prev.policyStatus1 += 1; 
			break; 
		case 2: 
			prev.policyStatus2 += 1; 
			break; 
		case 3: 
			prev.policyStatus3 += 1; 
			break;
		case 4: 
			prev.policyStatus4 += 1; 
			break;
		case 5: 
			prev.policyStatus5 += 1; 
			break;
	} 
	
	switch(doc.statusList[0].activityFlag) { 
		case 1: 
			prev.activityFlag1 += 1; 
		break; 
		case 2: 
			prev.activityFlag2 += 1; 
			break; 
		case 3: 
			prev.activityFlag3 += 1; 
			break;
		case 4: 
			prev.activityFlag4 += 1; 
			break;
		case 5: 
			prev.activityFlag5 += 1; 
			break;
	}
	
	
}