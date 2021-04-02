package com.example.a4kfullwalpapers.ui.home.filter

import android.content.Context
import android.graphics.BitmapFactory
import android.graphics.PointF
import com.example.a4kfullwalpapers.R
import jp.co.cyberagent.android.gpuimage.filter.*

fun filtersList(): FilterList {
    return FilterList().apply {
        addFilter("Contrast", FilterType.CONTRAST, R.drawable.contrast)
        addFilter("Invert", FilterType.INVERT, R.drawable.invert)
        addFilter("Pixelation", FilterType.PIXELATION,R.drawable.pixelation)
        addFilter("Hue", FilterType.HUE, R.drawable.hue)
        addFilter("Gamma", FilterType.GAMMA, R.drawable.gamma)
        addFilter("Brightness", FilterType.BRIGHTNESS, R.drawable.brightness)
        addFilter("Sepia", FilterType.SEPIA, R.drawable.sepia)
        addFilter("Grayscale", FilterType.GRAYSCALE, R.drawable.grayscale)
        addFilter("Sharpness", FilterType.SHARPEN, R.drawable.sharpness)
        addFilter("Sobel Edge Detection", FilterType.SOBEL_EDGE_DETECTION, R.drawable.sobel)
        addFilter("Threshold Edge Detection", FilterType.THRESHOLD_EDGE_DETECTION, R.drawable.threshold_edge_detec)
        addFilter("3x3 Convolution", FilterType.THREE_X_THREE_CONVOLUTION, R.drawable.convolution)
        addFilter("Emboss", FilterType.EMBOSS, R.drawable.emboss)
        addFilter("Posterize", FilterType.POSTERIZE, R.drawable.posterize)
        addFilter("Grouped filters", FilterType.FILTER_GROUP, R.drawable.grouped_filters)
        addFilter("Saturation", FilterType.SATURATION, R.drawable.saturation)
        addFilter("Exposure", FilterType.EXPOSURE, R.drawable.exposure)
        addFilter("Highlight Shadow", FilterType.HIGHLIGHT_SHADOW, R.drawable.highlight_shadow)
        addFilter("Monochrome", FilterType.MONOCHROME, R.drawable.monochrome)
        addFilter("Opacity", FilterType.OPACITY, R.drawable.opacity)
        addFilter("RGB", FilterType.RGB, R.drawable.rgb)
        addFilter("White Balance", FilterType.WHITE_BALANCE, R.drawable.white_balance)
        addFilter("Vignette", FilterType.VIGNETTE, R.drawable.vignette)
        addFilter("ToneCurve", FilterType.TONE_CURVE, R.drawable.tone_curve)

        addFilter("Luminance", FilterType.LUMINANCE, R.drawable.luminance)
        addFilter("Luminance Threshold", FilterType.LUMINANCE_THRESHSOLD, R.drawable.luminance_threshold)

        addFilter("Blend (Difference)", FilterType.BLEND_DIFFERENCE, R.drawable.blend_difference)
        addFilter("Blend (Source Over)", FilterType.BLEND_SOURCE_OVER, R.drawable.blend_source_over)
        addFilter("Blend (Color Burn)", FilterType.BLEND_COLOR_BURN, R.drawable.blend_color_burn)
        addFilter("Blend (Color Dodge)", FilterType.BLEND_COLOR_DODGE, R.drawable.blend_color_dodge)
        addFilter("Blend (Darken)", FilterType.BLEND_DARKEN, R.drawable.blend_darken)
        addFilter("Blend (Dissolve)", FilterType.BLEND_DISSOLVE, R.drawable.blend_dissolve)
        addFilter("Blend (Exclusion)", FilterType.BLEND_EXCLUSION, R.drawable.blend_exclusion)
        addFilter("Blend (Hard Light)", FilterType.BLEND_HARD_LIGHT, R.drawable.blend_hard_light)
        addFilter("Blend (Lighten)", FilterType.BLEND_LIGHTEN, R.drawable.blend_lighten)
        addFilter("Blend (Add)", FilterType.BLEND_ADD, R.drawable.blend_add)
        addFilter("Blend (Divide)", FilterType.BLEND_DIVIDE, R.drawable.blend_divide)
        addFilter("Blend (Multiply)", FilterType.BLEND_MULTIPLY, R.drawable.blend_multiply)
        addFilter("Blend (Overlay)", FilterType.BLEND_OVERLAY, R.drawable.blend_overlay)
        addFilter("Blend (Screen)", FilterType.BLEND_SCREEN, R.drawable.blend_screen)
        addFilter("Blend (Alpha)", FilterType.BLEND_ALPHA, R.drawable.blend_alpha)
        addFilter("Blend (Color)", FilterType.BLEND_COLOR, R.drawable.blend_color)
        addFilter("Blend (Hue)", FilterType.BLEND_HUE, R.drawable.blend_hue)
        addFilter("Blend (Saturation)", FilterType.BLEND_SATURATION, R.drawable.blend_saturation)
        addFilter("Blend (Luminosity)", FilterType.BLEND_LUMINOSITY, R.drawable.blend_luminos)
        addFilter("Blend (Linear Burn)", FilterType.BLEND_LINEAR_BURN, R.drawable.blend_linear)
        addFilter("Blend (Soft Light)", FilterType.BLEND_SOFT_LIGHT, R.drawable.blend_solo_light)
        addFilter("Blend (Subtract)", FilterType.BLEND_SUBTRACT, R.drawable.blend_suntract)
        addFilter("Blend (Chroma Key)", FilterType.BLEND_CHROMA_KEY, R.drawable.blend_chroma_key)
        addFilter("Blend (Normal)", FilterType.BLEND_NORMAL, R.drawable.blend_normal)

        addFilter("Lookup (Amatorka)", FilterType.LOOKUP_AMATORKA, R.drawable.lookup_amatorka)
        addFilter("Gaussian Blur", FilterType.GAUSSIAN_BLUR, R.drawable.gauss_blur)
        addFilter("Crosshatch", FilterType.CROSSHATCH, R.drawable.crosshatch)

        addFilter("Box Blur", FilterType.BOX_BLUR, R.drawable.box_blur)
        addFilter("CGA Color Space", FilterType.CGA_COLORSPACE, R.drawable.cga_color_space)
        addFilter("Dilation", FilterType.DILATION, R.drawable.dilation)
        addFilter("Kuwahara", FilterType.KUWAHARA, R.drawable.kuwahara)
        addFilter("RGB Dilation", FilterType.RGB_DILATION, R.drawable.rgb_dilation)
        addFilter("Sketch", FilterType.SKETCH, R.drawable.sketch)
        addFilter("Toon", FilterType.TOON, R.drawable.toon)
        addFilter("Smooth Toon", FilterType.SMOOTH_TOON, R.drawable.smooth_toon)
        addFilter("Halftone", FilterType.HALFTONE, R.drawable.halftone)

        addFilter("Bulge Distortion", FilterType.BULGE_DISTORTION, R.drawable.bulge_dis)
        addFilter("Glass Sphere", FilterType.GLASS_SPHERE, R.drawable.glass_sphere)
        addFilter("Haze", FilterType.HAZE, R.drawable.haze)
        addFilter("Laplacian", FilterType.LAPLACIAN, R.drawable.laplacian)
        addFilter("Non Maximum Suppression", FilterType.NON_MAXIMUM_SUPPRESSION, R.drawable.n_m_sus)
        addFilter("Sphere Refraction", FilterType.SPHERE_REFRACTION, R.drawable.sphere_ref)
        addFilter("Swirl", FilterType.SWIRL, R.drawable.swirl)
        addFilter("Weak Pixel Inclusion", FilterType.WEAK_PIXEL_INCLUSION, R.drawable.week_p_i)
        addFilter("False Color", FilterType.FALSE_COLOR, R.drawable.false_color)

        addFilter("Color Balance", FilterType.COLOR_BALANCE, R.drawable.color_balance)

        addFilter("Levels Min (Mid Adjust)", FilterType.LEVELS_FILTER_MIN, R.drawable.levels)

        addFilter("Bilateral Blur", FilterType.BILATERAL_BLUR, R.drawable.bileteral_blur)

        addFilter("Zoom Blur", FilterType.ZOOM_BLUR, R.drawable.zoom_blur)

        addFilter("Transform (2-D)", FilterType.TRANSFORM2D, R.drawable.transform)

        addFilter("Solarize", FilterType.SOLARIZE, R.drawable.solorize)

        addFilter("Vibrance", FilterType.VIBRANCE, R.drawable.vibrance)
    }
}

fun createFilterForType(context: Context, type: FilterType): GPUImageFilter {
    return when (type) {
        FilterType.CONTRAST -> GPUImageContrastFilter(2.0f)
        FilterType.GAMMA -> GPUImageGammaFilter(2.0f)
        FilterType.INVERT -> GPUImageColorInvertFilter()
        FilterType.PIXELATION -> GPUImagePixelationFilter()
        FilterType.HUE -> GPUImageHueFilter(90.0f)
        FilterType.BRIGHTNESS -> GPUImageBrightnessFilter(1.5f)
        FilterType.GRAYSCALE -> GPUImageGrayscaleFilter()
        FilterType.SEPIA -> GPUImageSepiaToneFilter()
        FilterType.SHARPEN -> GPUImageSharpenFilter()
        FilterType.SOBEL_EDGE_DETECTION -> GPUImageSobelEdgeDetectionFilter()
        FilterType.THRESHOLD_EDGE_DETECTION -> GPUImageThresholdEdgeDetectionFilter()
        FilterType.THREE_X_THREE_CONVOLUTION -> GPUImage3x3ConvolutionFilter()
        FilterType.EMBOSS -> GPUImageEmbossFilter()
        FilterType.POSTERIZE -> GPUImagePosterizeFilter()
        FilterType.FILTER_GROUP -> GPUImageFilterGroup(
            listOf(
                GPUImageContrastFilter(),
                GPUImageDirectionalSobelEdgeDetectionFilter(),
                GPUImageGrayscaleFilter()
            )
        )
        FilterType.SATURATION -> GPUImageSaturationFilter(1.0f)
        FilterType.EXPOSURE -> GPUImageExposureFilter(0.0f)
        FilterType.HIGHLIGHT_SHADOW -> GPUImageHighlightShadowFilter(
            0.0f,
            1.0f
        )
        FilterType.MONOCHROME -> GPUImageMonochromeFilter(
            1.0f, floatArrayOf(0.6f, 0.45f, 0.3f, 1.0f)
        )
        FilterType.OPACITY -> GPUImageOpacityFilter(1.0f)
        FilterType.RGB -> GPUImageRGBFilter(1.0f, 1.0f, 1.0f)
        FilterType.WHITE_BALANCE -> GPUImageWhiteBalanceFilter(
            5000.0f,
            0.0f
        )
        FilterType.VIGNETTE -> GPUImageVignetteFilter(
            PointF(0.5f, 0.5f),
            floatArrayOf(0.0f, 0.0f, 0.0f),
            0.3f,
            0.75f
        )
        FilterType.TONE_CURVE -> GPUImageToneCurveFilter().apply {
            setFromCurveFileInputStream(context.resources.openRawResource(R.raw.tone_cuver_sample))
        }
        FilterType.LUMINANCE -> GPUImageLuminanceFilter()
        FilterType.LUMINANCE_THRESHSOLD -> GPUImageLuminanceThresholdFilter(0.5f)
        FilterType.BLEND_DIFFERENCE -> createBlendFilter(
            context,
            GPUImageDifferenceBlendFilter::class.java
        )
        FilterType.BLEND_SOURCE_OVER -> createBlendFilter(
            context,
            GPUImageSourceOverBlendFilter::class.java
        )
        FilterType.BLEND_COLOR_BURN -> createBlendFilter(
            context,
            GPUImageColorBurnBlendFilter::class.java
        )
        FilterType.BLEND_COLOR_DODGE -> createBlendFilter(
            context,
            GPUImageColorDodgeBlendFilter::class.java
        )
        FilterType.BLEND_DARKEN -> createBlendFilter(
            context,
            GPUImageDarkenBlendFilter::class.java
        )
        FilterType.BLEND_DISSOLVE -> createBlendFilter(
            context,
            GPUImageDissolveBlendFilter::class.java
        )
        FilterType.BLEND_EXCLUSION -> createBlendFilter(
            context,
            GPUImageExclusionBlendFilter::class.java
        )

        FilterType.BLEND_HARD_LIGHT -> createBlendFilter(
            context,
            GPUImageHardLightBlendFilter::class.java
        )
        FilterType.BLEND_LIGHTEN -> createBlendFilter(
            context,
            GPUImageLightenBlendFilter::class.java
        )
        FilterType.BLEND_ADD -> createBlendFilter(
            context,
            GPUImageAddBlendFilter::class.java
        )
        FilterType.BLEND_DIVIDE -> createBlendFilter(
            context,
            GPUImageDivideBlendFilter::class.java
        )
        FilterType.BLEND_MULTIPLY -> createBlendFilter(
            context,
            GPUImageMultiplyBlendFilter::class.java
        )
        FilterType.BLEND_OVERLAY -> createBlendFilter(
            context,
            GPUImageOverlayBlendFilter::class.java
        )
        FilterType.BLEND_SCREEN -> createBlendFilter(
            context,
            GPUImageScreenBlendFilter::class.java
        )
        FilterType.BLEND_ALPHA -> createBlendFilter(
            context,
            GPUImageAlphaBlendFilter::class.java
        )
        FilterType.BLEND_COLOR -> createBlendFilter(
            context,
            GPUImageColorBlendFilter::class.java
        )
        FilterType.BLEND_HUE -> createBlendFilter(
            context,
            GPUImageHueBlendFilter::class.java
        )
        FilterType.BLEND_SATURATION -> createBlendFilter(
            context,
            GPUImageSaturationBlendFilter::class.java
        )
        FilterType.BLEND_LUMINOSITY -> createBlendFilter(
            context,
            GPUImageLuminosityBlendFilter::class.java
        )
        FilterType.BLEND_LINEAR_BURN -> createBlendFilter(
            context,
            GPUImageLinearBurnBlendFilter::class.java
        )
        FilterType.BLEND_SOFT_LIGHT -> createBlendFilter(
            context,
            GPUImageSoftLightBlendFilter::class.java
        )
        FilterType.BLEND_SUBTRACT -> createBlendFilter(
            context,
            GPUImageSubtractBlendFilter::class.java
        )
        FilterType.BLEND_CHROMA_KEY -> createBlendFilter(
            context,
            GPUImageChromaKeyBlendFilter::class.java
        )
        FilterType.BLEND_NORMAL -> createBlendFilter(
            context,
            GPUImageNormalBlendFilter::class.java
        )

        FilterType.LOOKUP_AMATORKA -> GPUImageLookupFilter().apply {
            bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.lookup_amatorka)
        }
        FilterType.GAUSSIAN_BLUR -> GPUImageGaussianBlurFilter()
        FilterType.CROSSHATCH -> GPUImageCrosshatchFilter()
        FilterType.BOX_BLUR -> GPUImageBoxBlurFilter()
        FilterType.CGA_COLORSPACE -> GPUImageCGAColorspaceFilter()
        FilterType.DILATION -> GPUImageDilationFilter()
        FilterType.KUWAHARA -> GPUImageKuwaharaFilter()
        FilterType.RGB_DILATION -> GPUImageRGBDilationFilter()
        FilterType.SKETCH -> GPUImageSketchFilter()
        FilterType.TOON -> GPUImageToonFilter()
        FilterType.SMOOTH_TOON -> GPUImageSmoothToonFilter()
        FilterType.BULGE_DISTORTION -> GPUImageBulgeDistortionFilter()
        FilterType.GLASS_SPHERE -> GPUImageGlassSphereFilter()
        FilterType.HAZE -> GPUImageHazeFilter()
        FilterType.LAPLACIAN -> GPUImageLaplacianFilter()
        FilterType.NON_MAXIMUM_SUPPRESSION -> GPUImageNonMaximumSuppressionFilter()
        FilterType.SPHERE_REFRACTION -> GPUImageSphereRefractionFilter()
        FilterType.SWIRL -> GPUImageSwirlFilter()
        FilterType.WEAK_PIXEL_INCLUSION -> GPUImageWeakPixelInclusionFilter()
        FilterType.FALSE_COLOR -> GPUImageFalseColorFilter()
        FilterType.COLOR_BALANCE -> GPUImageColorBalanceFilter()
        FilterType.LEVELS_FILTER_MIN -> GPUImageLevelsFilter()
        FilterType.HALFTONE -> GPUImageHalftoneFilter()
        FilterType.BILATERAL_BLUR -> GPUImageBilateralBlurFilter()
        FilterType.ZOOM_BLUR -> GPUImageZoomBlurFilter()
        FilterType.TRANSFORM2D -> GPUImageTransformFilter()
        FilterType.SOLARIZE -> GPUImageSolarizeFilter()
        FilterType.VIBRANCE -> GPUImageVibranceFilter()
    }
}

fun createBlendFilter(
    context: Context,
    filterClass: Class<out GPUImageTwoInputFilter>
): GPUImageFilter {
    return try {
        filterClass.newInstance().apply {
            bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.ic_launcher)
        }
    } catch (e: Exception) {
        e.printStackTrace()
        GPUImageFilter()
    }
}

