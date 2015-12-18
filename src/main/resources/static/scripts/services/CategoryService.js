'use strict';

pforsterServices.service('CategoryService', ['$q', 'DataService', function($q, DataService) {
	
	function getCategories() {
		var deferred = $q.defer();
		DataService.getCategories().then(function(categoryResult) {
			deferred.resolve(categoryResult.data);
		});
		return deferred.promise;
	}

	return {
		getCategories : function() { return getCategories(); }
	};
}]);
