package com.atosorigin.mice.km.bean;

import java.io.Serializable;
import java.util.List;

import com.atosorigin.mice.km.vo.CiApplicationVO;
import com.atosorigin.mice.km.vo.CiImageVO;

public class CiAppImageBean implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7785994255443046268L;
	private CiApplicationVO cavo;
	private List<CiImageVO> civos;
	public CiApplicationVO getCavo() {
		return cavo;
	}
	public void setCavo(CiApplicationVO cavo) {
		this.cavo = cavo;
	}
	public List<CiImageVO> getCivos() {
		return civos;
	}
	public void setCivos(List<CiImageVO> civos) {
		this.civos = civos;
	}
	

}
