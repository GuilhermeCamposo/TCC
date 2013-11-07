
jQuery(function() {
});

jQuery.fn.extend({
	_toolTip: function(){
		this.each(function() {
			var $this = jQuery(this);
			$this.simpleTooltip({title: this.title});
		});
	},
	mascaraCpfCnpj: function() {
		this.each(function() {			
			var $this = jQuery(this);
					
			var isCpf = false;
			
			$this.unsetMask().setMask('cnpj');
			
			var meSetMask = function() {
				if(this.value.length == 0)
				{
					return true;
				}
				
				var $this = jQuery(this);
				
				if(isCpf == true && this.value.length > 14)
				{
					isCpf = false;
					$this.unsetMask().setMask('cnpj');
				}else if(isCpf == false && this.value.length < 15)
				{
					isCpf = true;
					$this.unsetMask().setMask('999.999.999-9999');	
				}
			};
			
			$this.bind('keyup keypress', meSetMask);
			meSetMask.call(this);
		});
	},
	parseBreakLineToBR: function()
	{
		this.each(function() {
			var $this = jQuery(this);
			var text = jQuery.trim($this.text());
			$this.html(replaceAll(text, "\\n", "<br/>"));
		});
	},
	timingClick: function()
	{
		return this.each(function() {
			var $this = jQuery(this);
			setTimeout(function(){
				$this.click();
			}, 500);
		});
	},
	onlyNumber: function(e) {
		if(e == null)
		{
			var $this = $(this);
			return $this.keypress(function (e) {
				if(e.ctrlKey)
					return true;
				
				if( e.which!=8 && e.which!=0 && (e.which<48 || e.which>57))
					return false;
			});
			
			return $this;
		}else
		{
			if(e.ctrlKey)
				return true;
			
			if( e.which!=8 && e.which!=0 && (e.which<48 || e.which>57))
				 return false;
			else
				return true;
		}
	},
	onlyCharacter: function(e) {
		if(e == null)
		{
			var $this = $(this);
			
			return $this.keypress(function (e) {
				if(e.ctrlKey)
					return true;
				
				if(!(e.which == 32 || e.which == 8 || e.which == 0 || (e.which >=65 && e.which <=90) || (e.which >=97 && e.which <=122) || (e.which >=193 && e.which <=300)))
					return false;
			});
			
			return $this;
		}else
		{
			if(e.ctrlKey)
				return true;
			
			if(!(e.which == 32 || e.which == 8 || e.which == 0 || (e.which >=65 && e.which <=90) || (e.which >=97 && e.which <=122) || (e.which >=193 && e.which <=300)))
				return false;
			else
				return true;
		}
	},
	toUpperCase: function(e) {
		this.each(function() {
			var $this = jQuery(this);

			var meSetMask = function(){
				$this.val($this.val().toUpperCase());
			};
			
			$this.bind('keyup keypress', meSetMask);
		});
	},
	valueDefault: function(e) {
		this.each(function() {
			var $this = jQuery(this);
			$this.val("FILTRAR");
			
			var meSetMask = function(){
				if($this.val() === ''){
					$this.val("filtrar");
				}
				else if($this.val() === "FILTRAR"){
					$this.val("");
				}
				$this.val($this.val().toUpperCase());
			};
			
			$this.bind('click blur', meSetMask);
			
			var toUpperCase = function(){
				$this.val($this.val().toUpperCase());
			};
			
			$this.bind('keyup keypress', toUpperCase);
		});
	}
});

function mostrarMenuLateral(){
	document.getElementById('navegue_on').style.display="block";
	document.getElementById('navegue').style.display="none";
}	
function esconderMenuLateral(){
	document.getElementById('navegue').style.display="block";
	document.getElementById('navegue_on').style.display="none";
}
	