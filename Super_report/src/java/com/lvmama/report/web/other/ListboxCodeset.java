package com.lvmama.report.web.other;

import java.util.Iterator;
import java.util.List;

import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;

import com.lvmama.comm.pet.po.pub.CodeItem;
import com.lvmama.comm.pet.po.pub.CodeSet;

public class ListboxCodeset extends Listbox{

	private static final long serialVersionUID = 1L;
	private String codeset;

	public String getCodeset() {
		return codeset;
	}

	public void setCodeset(String codeset) {
		this.codeset = codeset;
		Listitem listitem = new Listitem();
		listitem.setLabel("-- 请选择  --");
		listitem.setValue("");
		this.appendChild(listitem);
		List<CodeItem> list=CodeSet.getInstance().getCodeList(codeset);
		for (Iterator<CodeItem> iter = list.iterator(); iter.hasNext();) {
			CodeItem item =  iter.next();
			listitem = new Listitem();
			listitem.setLabel(item.getName());
			listitem.setValue(item.getCode());
			this.appendChild(listitem);
		}
	}
	
}
