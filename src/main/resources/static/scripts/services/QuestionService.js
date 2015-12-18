'use strict';

pforsterServices.service('QuestionService', ['$q', 'DataService', function($q, DataService) {
	
	function saveQuestion(question, type) {
		var deferred = $q.defer();
		
		if(!question.category.id) {
			question.category = { id : null, name : question.category };
		}
		
		if(type == 'simple') {
			DataService.saveSimpleQuestion(question).then(function(result) {
				deferred.resolve(result.data);
			});
		} else if(type == 'image') {
			question.imageUpload = question.imageUpload.files[0].name;
			DataService.saveImageQuestion(question).then(function(result) {
				deferred.resolve(result.data);
			});
		} else if(type == 'complement') {
			DataService.saveComplementQuestion(question).then(function(result) {
				deferred.resolve(result.data);
			});
		}
		
		return deferred.promise;
	}
	
	return {
		saveQuestion : function(question, type) { return saveQuestion(question, type); }
	};
}]);
