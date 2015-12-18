'use strict';

pforsterServices.service('DataService', ['$q', '$http', function ($q, $http) {
	
	function getCategories() {
		return getData("/category/all", true);
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
	
	function postMultipartFormData(url, data) {
		$http({
            method: 'POST',
            url: url,
            headers: { 'Content-Type': false },
            transformRequest: function (data) {
                var formData = new FormData();
//                formData.append("model", angular.toJson(data));
                formData.append("file", data);
                return formData;
            },
            //Create an object that contains the model and files which will be transformed
            // in the above transformRequest method
            data: data
        }).
        success(function (data, status, headers, config) {
            alert("success!");
        }).
        error(function (data, status, headers, config) {
            alert("failed!");
        });
	}
	
	function postFormData(url, data) {
		var deferred = $q.defer();
		
		$http({
			method : 'POST',
			url : url,
			headers : { 'Content-Type' : 'application/x-www-form-urlencoded'},
			transformRequest : function(obj) {
				var str = [];
				for (var p in obj)
				str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
				return str.join("&");
			},
			data : data
		}).then(function(result) {
			deferred.resolve(result);
		}, function() {
			deferred.reject("Unable to post data to: " + url);
		});
		
		return deferred.promise;
	}
	
	return {
		getCategories : function() { return getCategories(); },
		saveSimpleQuestion : function(question) { return saveSimpleQuestion(question); },
		saveImageQuestion : function(question) { return saveImageQuestion(question); },
		saveComplementQuestion : function(question) { return saveComplementQuestion(question); }
	};
}]);
