/*
 * Copyright 2012 Mark Mandel
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.compoundtheory.intellij.tmux.actions;

import com.compoundtheory.intellij.tmux.TmuxPlugin;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.util.IconLoader;

import javax.swing.*;

/**
 * @author Mark Mandel
 */
public class SelectPaneAction extends AnAction
{
	private String target;
	private String title;

	public SelectPaneAction()
	{
		super("-");
	}

	public SelectPaneAction(String target, String title, boolean selected)
	{
		super(title, title, determineIcon(selected));
		this.target = target;
		this.title = title;
	}

	public void actionPerformed(AnActionEvent e)
	{
		//Messages.showMessageDialog("Selected " + target, "Information", Messages.getInformationIcon());
		TmuxPlugin.currentTarget = this.target;

		Messages.showMessageDialog("Tmux Pane '" + this.title + "' selected.", "Tmux Pane Selected", Messages.getInformationIcon());
	}

	private static Icon determineIcon(boolean selected)
	{
		Icon icon = null;

		if(selected)
		{
			icon = IconLoader.getIcon("/actions/checked.png");
		}

		return icon;
	}
}
