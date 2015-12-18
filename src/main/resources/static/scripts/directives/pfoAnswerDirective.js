pforsterDirectives.directive('pfoAnswer', function(){
	return {
        restrict: 'E',
        scope: {
        	index: "@index",
        	label: "@label",
        	question : "=question"
        },
		templateUrl: "/directives/pfoAnswer.html"
        
    };
});