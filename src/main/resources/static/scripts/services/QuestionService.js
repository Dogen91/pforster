'use strict';

pforsterServices.service('QuestionService', ['$q', 'DataService', function($q, DataService) {
	
	function getQuestionsByCategoryAndMode(categoryId, mode) {
		var deferred = $q.defer();
		
		DataService.getQuestionsByCategoryAndMode(categoryId, mode).then(function(result) {
			var questions = [];
			angular.forEach(result.data, function(question) {
				if(question.type == 'COMPLEMENT'){
					var numberOfAnswers = question.answers.length;
					var numberOfAnswersToGive = Math.round(numberOfAnswers * 0.6);
					question.answersToGive = reservoirSampling(numberOfAnswers, numberOfAnswersToGive, question.answers);
				} else {
					questions.push(question);
				}
			});
			deferred.resolve(result.data);
		})
		
		return deferred.promise;
	}
	
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
	
	function reservoirSampling(n, k, s){
		var result = [];
		for(var i = 0; i < k; i++) {
			result[i] = s[i];
		}
		
		for(var i = k; i < n; i++) {
			var j = Math.floor(Math.random() * (i + 1));
			if(j < k) {
				result[j] = s[i];
			}
		}
		return result;
	}
	
	return {
		getQuestionsByCategoryAndMode : function(categoryId, mode) { return getQuestionsByCategoryAndMode(categoryId, mode); },
		saveQuestion : function(question, type) { return saveQuestion(question, type); }
	};
}]);
