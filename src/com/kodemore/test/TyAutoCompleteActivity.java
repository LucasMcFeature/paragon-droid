/*
  Copyright (c) 2005-2012 Wyatt Love

  Permission is hereby granted, free of charge, to any person obtaining a copy
  of this software and associated documentation files (the "Software"), to deal
  in the Software without restriction, including without limitation the rights
  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
  copies of the Software, and to permit persons to whom the Software is
  furnished to do so, subject to the following conditions:

  The above copyright notice and this permission notice shall be included in
  all copies or substantial portions of the Software.

  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
  THE SOFTWARE.
 */

package com.kodemore.test;

import android.view.View;

import com.kodemore.collection.KmList;
import com.kodemore.utility.KmSamples;
import com.kodemore.view.KmActivity;
import com.kodemore.view.KmAutoCompleteTextView;
import com.kodemore.view.KmColumnLayout;

/**
 * Example of a very simple list.
 * By using our subclass we can simplify the setup of simple lists.
 */
public class TyAutoCompleteActivity
    extends KmActivity
{
    //##################################################
    //# variables
    //##################################################

    private KmList<String> _items;

    //##################################################
    //# init
    //##################################################

    @Override
    protected void init()
    {
        _items = KmSamples.getAnimalTypes();
    }

    //##################################################
    //# layout
    //##################################################

    @Override
    protected View createLayout()
    {

        KmAutoCompleteTextView<String> view;
        view = newAnimalField();

        KmColumnLayout root;
        root = ui().newColumn();
        root.addView(view);
        return root;
    }

    private KmAutoCompleteTextView<String> newAnimalField()
    {
        return new KmAutoCompleteTextView<String>(ui())
        {
            @Override
            protected KmList<String> performFiltering(String search)
            {
                KmList<String> results;
                results = new KmList<String>();

                for ( String s : _items )
                    if ( s.contains(search) )
                        results.add(s);

                return results;
            }
        };
    }
}
