define([],function(){
	var config = {};
        config.bProcessing= true;
        config.iDisplayLength= 50;
        config.sPaginationType= "full_numbers";
        config.aaSorting= [[0,"desc"]];
        config.sScrollX= "100%";
		config.sScrollXInner= "101%";
		config.bScrollCollapse= true;
		config.columnWidths = ['50%','25%','10%','15%'];
        config.sDom= '<"top"lf<"clear">ip<"clear">>rt<"bottom"ip<"clear">';
        
	return config;
});