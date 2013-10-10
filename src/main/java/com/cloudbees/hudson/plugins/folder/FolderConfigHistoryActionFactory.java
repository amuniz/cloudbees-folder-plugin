/*
 * The MIT License
 *
 * Copyright 2013 CloudBees.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package com.cloudbees.hudson.plugins.folder;

import hudson.Extension;
import hudson.model.Action;
import hudson.plugins.jobConfigHistory.JobConfigHistoryProjectAction;
import java.util.Collection;
import java.util.Collections;
import jenkins.model.Jenkins;

@Extension(optional=true)
public class FolderConfigHistoryActionFactory extends TransientFolderActionFactory {

    @Override public Collection<? extends Action> createFor(Folder target) {
        if (Jenkins.getInstance().getPlugin("jobConfigHistory") != null) {
            return Collections.singleton(make(target));
        } else {
            return Collections.emptySet();
        }
    }

    // Separated into its own method to avoid resolving when this class is loaded.
    // May or may not be necessary, depending on JVM (spec is unclear on this point), but cannot hurt.
    private static Action make(Folder target) {
        return new JobConfigHistoryProjectAction(target);
    }

}