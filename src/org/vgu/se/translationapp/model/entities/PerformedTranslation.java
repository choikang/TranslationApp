package org.vgu.se.translationapp.model.entities;

import java.io.Serializable;

public class PerformedTranslation implements Serializable {
	


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((expressionEN == null) ? 0 : expressionEN.hashCode());
		result = prime * result + ((expressionGER == null) ? 0 : expressionGER.hashCode());
		result = prime * result + userID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PerformedTranslation other = (PerformedTranslation) obj;
		if (expressionEN == null) {
			if (other.expressionEN != null)
				return false;
		} else if (!expressionEN.equals(other.expressionEN))
			return false;
		if (expressionGER == null) {
			if (other.expressionGER != null)
				return false;
		} else if (!expressionGER.equals(other.expressionGER))
			return false;
		if (userID != other.userID)
			return false;
		return true;
	}

	private int userID;
	
	private String expressionGER = null;
	
	private String expressionEN = null;
	
	public String getExpressionGER() {
		return expressionGER;
	}

	public void setExpressionGER(String expressionGER) {
		this.expressionGER = expressionGER;
	}

	public String getExpressionEN() {
		return expressionEN;
	}

	public void setExpressionEN(String expressionEN) {
		this.expressionEN = expressionEN;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}


	
	

}
