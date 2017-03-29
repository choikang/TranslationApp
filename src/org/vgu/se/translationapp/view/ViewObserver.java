package org.vgu.se.translationapp.view;

import org.vgu.se.translationapp.model.entities.PerformedTranslation;

public interface ViewObserver {
	public void update(PerformedTranslation translate);
	
	
}
