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

import com.compoundtheory.intellij.tmux.LanguageHooks;
import com.compoundtheory.intellij.tmux.Tmux;
import com.compoundtheory.intellij.tmux.TmuxPlugin;
import com.compoundtheory.intellij.tmux.config.TmuxProjSettings;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.DataKeys;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.Messages;

/**
 * @author Mark Mandel
 */
public class SendSelectedTextAction extends AnAction
{
	public void actionPerformed(AnActionEvent e)
	{
		//thanks La Clojure - https://github.com/JetBrains/la-clojure/blob/clojure-idea-11/src/org/jetbrains/plugins/clojure/repl/actions/RunSelectedTextAction.java
		Editor editor = e.getData(DataKeys.EDITOR);

		if(editor == null)
		{
			return;
		}

		SelectionModel selectionModel = editor.getSelectionModel();
		String selectedText = selectionModel.getSelectedText();
		Project project = e.getData(DataKeys.PROJECT);
		TmuxProjSettings settings = TmuxProjSettings.getInstance(project);

		if (selectedText == null || selectedText.trim().length() == 0) {
			return;
		}

		if(TmuxPlugin.currentTarget == null)
		{
			Messages.showErrorDialog(e.getProject(), "Please select a pane before sending text to Tmux.", "Error sending data to Tmux");
			return;
		}
		selectedText = LanguageHooks.check(e, selectedText);

		Tmux.getInstance().sendText(selectedText, TmuxPlugin.currentTarget, settings);
	}
}
