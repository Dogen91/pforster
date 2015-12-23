'use strict';

pforsterControllers.controller('QuizCtrl', ['$scope', '$routeParams', '$timeout', 'QuestionService', function ($scope, $routeParams, $timeout, QuestionService) {

	$scope.currentQuestionIndex = 0;
	$scope.numberOfQuestions = 0;
	
	QuestionService.getQuestionsByCategoryAndMode($routeParams.categoryId, $routeParams.mode).then(function(questions){
		$scope.questions = questions;
		$scope.numberOfQuestions = questions.length;
	});
	
	
	$scope.checkTextAnswer = function(helpCount, answer, userAnswer){
		if(answer.toLowerCase() === userAnswer.toLowerCase()) {
			// TODO pforster: save answer
			
			displayNextQuestion();
		} else {
			if(helpCount == 3) {
				// TODO pforster: save answer
				
				$timeout(function(){
					displayNextQuestion();
		        },1000);
				
				return getTextHelp(helpCount, answer);
			}else {
				return getTextHelp(helpCount, answer);
			}
		}
	};
	
	$scope.checkComplementAnswers = function(helpCount, help, answers, userAnswers) {
		var helpAnswers = [].concat(answers);
		var wrongAnswers = [];
		angular.forEach(userAnswers, function(userAnswer, index){
			var isCorrect = false;
			angular.forEach(answers, function(answer) {
				if(answer.toLowerCase() == userAnswer.toLowerCase()) {
					helpAnswers.splice(helpAnswers.indexOf(answer), 1);
					help[index] = '';
					isCorrect = true;
				}
			});
			if(!isCorrect){
				wrongAnswers.push(index);
			}
		});
		
		if(wrongAnswers.length == 0) {
			// TODO pforster: save answer
			
			displayNextQuestion();
		} else {
			if(helpCount == 3) {
				// TODO pforster: save answer
				
				$timeout(function(){
					displayNextQuestion();
		        },1000);
				angular.forEach(helpAnswers, function(helpAnswer) {
					help[wrongAnswers.splice(0,1)] = getTextHelp(helpCount, helpAnswer);
				});
			}else {
				angular.forEach(helpAnswers, function(helpAnswer) {
					help[wrongAnswers.splice(0,1)] = getTextHelp(helpCount, helpAnswer);
				});
			}
		}
	}
	
	function displayNextQuestion(){
		if($scope.currentQuestionIndex < $scope.numberOfQuestions) {
			$scope.currentQuestionIndex++;
		}
	}
	
	function getTextHelp(helpCount, answer){
		var threshold = answer.length / 100 * (helpCount * 33.33);
		var help = "";
		angular.forEach(answer, function(letter, index) {
			if(index < threshold) {
				help += letter;
			}else if(letter == ' '){
				help += " ";
			}else {
				help += "_";
			}
		});
		return help;
	};
}]);