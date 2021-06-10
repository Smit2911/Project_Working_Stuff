package op.mobile.app.dev.kaths4.travelling.ui.quiz

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import op.mobile.app.dev.kaths4.travelling.model.Quiz
import op.mobile.app.dev.kaths4.travelling.service.ServiceInstance
import op.mobile.app.dev.kaths4.travelling.service.ServiceStatus

class QuizDetailModel : ViewModel() {
    // TODO: Implement the ViewModel
    private val _status = MutableLiveData<ServiceStatus>()
    val status: LiveData<ServiceStatus> get() = _status

    private val _response = MutableLiveData<List<Quiz>>()
    val response: LiveData<List<Quiz>> get() = _response

    init {
        viewModelScope.launch {
            _status.value = ServiceStatus.LOADING
            try {
                _response.value = ServiceInstance.retrofitService.getQuiz()
                _status.value = ServiceStatus.COMPLETE
            } catch (e: Exception) {
                _response.value = ArrayList()
                _status.value = ServiceStatus.ERROR
            }
        }
    }
}