import {RiotComponentExport} from 'riot'
import {FrequentlyAskedQuestionDetail} from '../../axios-front/model/frequently-asked-question-detail';

export interface FrequentlyAskedQuestionListComponentState {
    frequently_asked_questions?: Array<FrequentlyAskedQuestionDetail>;
}

export interface FrequentlyAskedQuestionListComponentProps {
}

export interface FrequentlyAskedQuestionListComponent extends RiotComponentExport<FrequentlyAskedQuestionListComponentProps, FrequentlyAskedQuestionListComponentState> {
    state: FrequentlyAskedQuestionListComponentState;

    frequentlyAskedQuestionSearch(e): void;
}