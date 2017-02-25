/*  Copyright (c) 2014 Austin Chamney, achamney@gmail.com. Isabella Vieira, isabellavieira57@gmail.com.
    Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions: The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software. THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
*/
define(['dom/tableControl','dom/form/select'],function(){
     quickforms.loadCalendar = function(config){ //appName, queryName*, parameterList,whereclause, callback, domId*,configFile, formId*, selectName*
         
         quickforms.parseForm({formId:config.formId,
                                fact:Math.random()});
         var formObj = quickforms['currentForm'+config.formId];
         
		formObj.completedListeners.push(function(){
			if(isNull(config.parameterList))
			{
				config.parameterList =config.selectName+'='+formObj.childMap[config.selectName].selectedField; 
			
			} else {
				config.parameterList += ','+config.selectName+'='+formObj.childMap[config.selectName].selectedField;
			}
			var tableCallback = function(){
				var tableDiv = $('#'+config.domId);
				 tableDiv.undelegate('tbody tr td','click');

				 tableDiv.delegate('tbody tr td', 'click', function () {
					var aData = tableDiv.dataTable().fnGetData( this );
				} );

				 if(config.oldCallback){config.oldCallback();}
			 };
			 config.oldCallback = config.callback;
			 config.callback = tableCallback;
			 quickforms.loadTable(config);
			 
			
			formObj.childMap[config.selectName].dom.on('change',function(eventData){			
				window.setTimeout(function(){location.reload(true);},200);
			});
			 
		});
         
         
       
     };
});