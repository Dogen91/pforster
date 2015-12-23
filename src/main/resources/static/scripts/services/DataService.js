'use strict';

pforsterServices.service('DataService', ['$q', '$http', function ($q, $http) {
	
	function getCategories() {
		return getData("/category/all", true);
	}
	
	function getQuestionsByCategoryAndMode(categoryId, mode) {
		return getData("/question/all/" + categoryId + "/" + mode);
	}
	
	function saveSimpleQuestion(question) {
		return postData("/question/addQuestion/simple", question, true);
	}
	
	function saveImageQuestion(question) {
		return postData("/question/addQuestion/image", question, true);
	}

	function saveComplementQuestion(question) {
		return postData("/question/addQuestion/complement", question, true);
	}
	
	function getData(url, doCache) {
		var deferred = $q.defer();
		$http.get(url, {
			cache : doCache
		}).then(function(result) {
			deferred.resolve(result);
		}, function() {
			deferred.reject("Unable to load data from: " + url);
		});
		return deferred.promise;
	}
	
	function postData(url, data, doCache) {
		var deferred = $q.defer();
		$http.post(url, data, {
			cache : doCache
		}).then(function(result) {
			deferred.resolve(result);
		}, function() {
			deferred.reject("Unable to post data to: " + url);
		});
		return deferred.promise;
	}
	
	return {
		getCategories : function() { return getCategories(); },
		getQuestionsByCategoryAndMode : function(categoryId, mode) { return getQuestionsByCategoryAndMode(categoryId, mode); },
		saveSimpleQuestion : function(question) { return saveSimpleQuestion(question); },
		saveImageQuestion : function(question) { return saveImageQuestion(question); },
		saveComplementQuestion : function(question) { return saveComplementQuestion(question); }
	};
}]);
