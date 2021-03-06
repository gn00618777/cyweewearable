package cwm.usingwearablesdk;

/**
 * Created by user on 2017/9/10.
 */
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import cwm.wearablesdk.constants.ID;

public class FactoryFragment extends Fragment {

    private View mView;
    private Button selfTestADXL;
    private Button selfTestBMIACC;
    private Button selfTestBMIGYRO;
    private Button selfTestHEART;
    private Button selfTestVIBRATOR;
    private Button selfTestBUTTON;
    private Button selfTestOLED;
    private Button selfTestFLASH;
    private Button clearSelfTest;
    private Button calibrateADXL;
    private Button calibrateBMIACC;
    private Button calibrateBMIGYRO;
    private Button clearCalibrate;
    private TextView selfTestView;
    private TextView calibrateView;

    private boolean dfu = false;
    private boolean selfTest = false;

    String selfTestResult = "";
    String calibrateResult = "";

    int progress = 1;

    private ListenForFactoryFragment mCallback;

    public interface ListenForFactoryFragment{
        //void onPressDFUButton();
        void onPressSelfTestButton(int componet);
        void onPressClearSelfTestTResultButton();
        void onPressCalibrateButton(int comonet);
        void onPressClearCalibrateButton();
        void onPressRecordSensorDataButton();
    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        try {
            mCallback = (ListenForFactoryFragment)context;

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement ListenForFactoryFragment");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mView = inflater.inflate(R.layout.layout_factory, null);
        }

        selfTestADXL = (Button)mView.findViewById(R.id.self_test_adxl_acc);
        selfTestADXL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onPressSelfTestButton(ID.ADXL_ACC);
            }
        });
        selfTestBMIACC = (Button)mView.findViewById(R.id.self_test_bmi160_acc);
        selfTestBMIACC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onPressSelfTestButton(ID.BMI160_ACC);
            }
        });
        selfTestBMIGYRO = (Button)mView.findViewById(R.id.self_test_bmi160_gyro);
        selfTestBMIGYRO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onPressSelfTestButton(ID.BMI160_GYRO);
            }
        });
        selfTestHEART = (Button)mView.findViewById(R.id.self_test_heart);
        selfTestHEART.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onPressSelfTestButton(ID.HEART_RATAE);
            }
        });
        selfTestVIBRATOR = (Button)mView.findViewById(R.id.self_test_vibrate);
        selfTestVIBRATOR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onPressSelfTestButton(ID.VIBRATE_RATE);
            }
        });
        selfTestBUTTON = (Button)mView.findViewById(R.id.self_test_button);
        selfTestBUTTON.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onPressSelfTestButton(ID.BUTTON);
            }
        });
        selfTestOLED = (Button)mView.findViewById(R.id.self_test_oled);
        selfTestOLED.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onPressSelfTestButton(ID.OLED);
            }
        });
        selfTestFLASH = (Button)mView.findViewById(R.id.self_test_flash);
        selfTestFLASH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onPressSelfTestButton(ID.FLASH);
            }
        });
        clearSelfTest = (Button)mView.findViewById(R.id.clear_result);
        clearSelfTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onPressClearSelfTestTResultButton();
            }
        });

        calibrateADXL = (Button)mView.findViewById(R.id.calibrate_adxl);
        calibrateADXL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               mCallback.onPressCalibrateButton(ID.ADXL_ACC);
            }
        });
        calibrateBMIACC = (Button)mView.findViewById(R.id.calibrate_bmi160_acc);
        calibrateBMIACC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onPressCalibrateButton(ID.BMI160_ACC);
            }
        });
        calibrateBMIGYRO = (Button)mView.findViewById(R.id.calibrate_bmi160_gyro);
        calibrateBMIGYRO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onPressCalibrateButton(ID.BMI160_GYRO);
            }
        });
        if(progress == 1){
            calibrateADXL.setVisibility(View.VISIBLE);
            calibrateBMIACC.setVisibility(View.INVISIBLE);
            calibrateBMIGYRO.setVisibility(View.INVISIBLE);
        }
        else if(progress == 2){
            calibrateBMIACC.setVisibility(View.VISIBLE);
            calibrateADXL.setVisibility(View.INVISIBLE);
            calibrateBMIGYRO.setVisibility(View.INVISIBLE);
        }
        else if(progress == 3){
            calibrateBMIGYRO.setVisibility(View.VISIBLE);
            calibrateADXL.setVisibility(View.INVISIBLE);
            calibrateBMIACC.setVisibility(View.INVISIBLE);
        }
        else if(progress == 4){
            calibrateADXL.setVisibility(View.VISIBLE);
            calibrateBMIACC.setVisibility(View.INVISIBLE);
            calibrateBMIGYRO.setVisibility(View.INVISIBLE);
        }

        clearCalibrate = (Button)mView.findViewById(R.id.clear_calibrate_resut);
        clearCalibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onPressClearCalibrateButton();
            }
        });
        selfTestView = (TextView)mView.findViewById(R.id.self_test_result);
        selfTestView.setText(selfTestResult);
        calibrateView = (TextView)mView.findViewById(R.id.calibrate_result);
        calibrateView.setText(calibrateResult);

        return mView;
    }

    public void updateResult(String selfTest, String calibrate){
        selfTestResult = selfTest;
        calibrateResult = calibrate;
    }

    public void updateProgress(int progress){
        this.progress = progress;
    }

}
